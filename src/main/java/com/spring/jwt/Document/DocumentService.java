package com.spring.jwt.Document;

import com.spring.jwt.utils.BaseResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    BaseResponseDTO uploadDocument(Integer userId , String documentName, List<MultipartFile> file);
}
