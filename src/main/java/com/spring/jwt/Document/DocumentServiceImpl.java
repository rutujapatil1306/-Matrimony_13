package com.spring.jwt.Document;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.Document;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.*;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.ApiResponse;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final CompleteProfileRepository completeProfileRepository;

    private final ObjectMapper objectMapper = new ObjectMapper(); // For JSON convert




    @Override
    public BaseResponseDTO uploadDocument(Integer userId, List<String> documentName, List<MultipartFile> files) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        if (documentRepository.existsByUserId(userId)) {
            throw new DuplicateResourceException(
                    "Documents already exist for this user"
            );
        }

        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("Please upload at least one file");
        }

        if (documentName.size() != files.size()) {
            throw new MissingDocumentNameException("Each file must have a corresponding document name");
        }

        CompleteProfile completeProfile = completeProfileRepository.findByUserId(userId);
        if (completeProfile == null) {
            throw new UserNotFoundExceptions("Complete profile not found for user: " + userId);
        }

        List<Integer> documentIdList = convertJsonToList(completeProfile.getDocumentIds());


        for (int i = 0; i < files.size(); i++) {

            MultipartFile file = files.get(i);
            String docType = documentName.get(i);

            String contentType = file.getContentType();

            if (!isAllowedFile(contentType)) {
                throw new RuntimeException("Only PDF, JPEG, JPG, PNG allowed");
            }

            byte[] compressedBytes;
            try {
                if (contentType.equals("application/pdf")) {
                    compressedBytes = compressPDF(file);
                } else {
                    compressedBytes = compressImage(file);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error during file compression: " + e.getMessage());
            }

            // Size validation (after compression)
            if (compressedBytes.length > (5 * 1024 * 1024)) {
                throw new RuntimeException("File exceeds 5MB after compression");
            }

            Document document = new Document();
            document.setUser(user);
            document.setDocumentName(docType);
            document.setFileData(compressedBytes);

            Document savedDoc = documentRepository.save(document);

            documentIdList.add(savedDoc.getDocumentId());
        }

        completeProfile.setDocumentIds(convertListToJson(documentIdList));

        completeProfileRepository.save(completeProfile);

        // 7. Response
        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode("200");
        response.setMessage("Documents uploaded successfully");


        return response;
    }

    @Override
    public ApiResponse getDocumentByName(Integer userId, String documentName) {

        // Validate user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        // Fetch document
        Document document = (Document) documentRepository
                .findByUserIdAndDocumentName(userId, documentName)
                .orElseThrow(() -> new DocumentNotFoundException(
                        "No document found with name: " + documentName
                ));

        // Convert byte[] → Base64 string
        String base64Data = Base64.getEncoder().encodeToString(document.getFileData());

        // Prepare DTO
        DocumentDTO dto = new DocumentDTO();
        dto.setDocumentId(document.getDocumentId());
        dto.setDocumentName(document.getDocumentName());
        dto.setFileData(base64Data);

        // Prepare response
        ApiResponse response = new ApiResponse();
        response.setSuccess(true);
        response.setStatusCode(200);
        response.setMessage("Document found");
        response.setData(dto);

        return response;
    }





    // ============================================================
    //            PDF & IMAGE COMPRESSION METHODS
    // ============================================================

    private byte[] compressPDF(MultipartFile file) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfReader reader = new PdfReader(file.getInputStream());
        PdfWriter writer = new PdfWriter(outputStream,
                new WriterProperties().setCompressionLevel(9)); // max compression

        PdfDocument pdfDoc = new PdfDocument(reader, writer);
        pdfDoc.close();

        return outputStream.toByteArray();
    }


    private byte[] compressImage(MultipartFile file) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Thumbnails.of(file.getInputStream())
                .size(1024, 1024)
                .outputQuality(0.7)
                .toOutputStream(outputStream);

        return outputStream.toByteArray();
    }


    // ============================================================
    //                VALIDATION METHODS
    // ============================================================

    private boolean isAllowedFile(String contentType) {
        return contentType.equals("application/pdf") ||
                contentType.equals("image/jpeg") ||
                contentType.equals("image/png");
    }


    // ============================================================
    //                 JSON UTILITY METHODS
    // ============================================================

    private List<Integer> convertJsonToList(String json) {
        try {
            if (json == null || json.isEmpty()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(json, new TypeReference<List<Integer>>() {});
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    private String convertListToJson(List<Integer> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            return "[]";
        }
    }

}
