package com.spring.jwt.Document;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.spring.jwt.CompleteProfile.CompleteProfileRepository;
import com.spring.jwt.entity.CompleteProfile;
import com.spring.jwt.entity.Document;
import com.spring.jwt.entity.User;
import com.spring.jwt.exception.UserNotFoundExceptions;
import com.spring.jwt.repository.UserRepository;
import com.spring.jwt.utils.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService{

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final CompleteProfileRepository completeProfileRepository;

//    @Override
//    public BaseResponseDTO uploadDocument(Integer userId ,String documentName, MultipartFile file) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));
//
//
//
//
//        CompleteProfile completeProfile = new CompleteProfile();
//        completeProfile.setEducationAndProfession(save);
//        completeProfileRepository.save(completeProfile);
//
//        BaseResponseDTO response = new BaseResponseDTO();
//        response.setCode("200");
//        response.setMessage("Education Saved Successfully");
//        response.setID(save.getEducationId());
//
//
//        return response;
//    }

    @Override
    public BaseResponseDTO uploadDocument(Integer userId, String documentType, List<MultipartFile> files) {

        // -------- 1. Validate User --------
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found"));

        if (files == null || files.isEmpty()) {
            throw new RuntimeException("Please upload at least one file");
        }

        List<Integer> savedDocumentIds = new ArrayList<>();

        for (MultipartFile file : files) {

            // -------- 2. Validate file type --------
            String contentType = file.getContentType();

            if (!isAllowedFile(contentType)) {
                throw new RuntimeException("Only PDF, JPEG, and PNG files are allowed.");
            }

            // -------- 3. Compress file --------
            byte[] compressedBytes;
            try {
                if (contentType.equals("application/pdf")) {
                    compressedBytes = compressPDF(file);
                } else {
                    compressedBytes = compressImage(file);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error while compressing file: " + e.getMessage());
            }

            // -------- 4. Validate final size --------
            if (compressedBytes.length > (5 * 1024 * 1024)) {
                throw new RuntimeException("File exceeds 5MB after compression.");
            }

            // -------- 5. Save Document --------
            Document document = new Document();
            document.setUser(user);
            document.setDocumentType(documentType);
            document.setFileName(file.getOriginalFilename());
            document.setFileData(compressedBytes);

            Document savedDoc = documentRepository.save(document);
            savedDocumentIds.add(savedDoc.getDocumentId());

            CompleteProfile completeProfile = completeProfileRepository.findByUserId(userId);
            completeProfile.setDocument(document);

            //completeProfile.setUser(user);
            completeProfileRepository.save(completeProfile);

        }
//        // add all uploaded document IDs
//        if (completeProfile.getDocuments() == null) {
//            completeProfile.setDocuments(new ArrayList<>());
//        }

//        List<Document> allDocuments = documentRepository.findAllById(savedDocumentIds);
//        completeProfile.getDocuments().addAll(allDocuments);

            // -------- 7. Prepare Response --------
            BaseResponseDTO response = new BaseResponseDTO();
            response.setCode("200");
            response.setMessage("Documents uploaded successfully");
            // response.setID(); // Return list of document IDs

            return response;
        }


    private byte[] compressPDF(MultipartFile file) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfReader reader = new PdfReader(file.getInputStream());
        PdfWriter writer = new PdfWriter(outputStream,
                new WriterProperties().setCompressionLevel(9));

        PdfDocument pdfDoc = new PdfDocument(reader, writer);
        pdfDoc.close();

        return outputStream.toByteArray();

    }

    private boolean isAllowedFile(String contentType) {
        return contentType.equals("application/pdf") ||
                contentType.equals("image/jpeg") ||
                contentType.equals("image/png");
    }

    private byte[] compressImage(MultipartFile file) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Thumbnails.of(file.getInputStream())
                .size(1024, 1024)
                .outputQuality(0.7)
                .toOutputStream(outputStream);

        return outputStream.toByteArray();
    }


}
