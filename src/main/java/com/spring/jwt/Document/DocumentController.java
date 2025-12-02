package com.spring.jwt.Document;

import com.spring.jwt.utils.BaseResponseDTO;
import com.spring.jwt.utils.SecurityUtil;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;


    @PostMapping("/upload")
    public ResponseEntity<BaseResponseDTO> uploadDocuments(
            @RequestParam String documentName,
            @RequestPart List<MultipartFile> file)
    {
        Integer userId= SecurityUtil.getCurrentUserId();
        BaseResponseDTO response = documentService.uploadDocument(userId ,documentName, file);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


}
