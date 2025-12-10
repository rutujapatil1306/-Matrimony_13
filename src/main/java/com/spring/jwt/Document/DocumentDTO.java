package com.spring.jwt.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {

    @NotBlank(message = "Document name required")
    private String documentName;

    @NotNull(message = "Document file is required")
    @Size(min = 1, max = 5_000_000, message = "File size must be between 1 byte and 5 MB")
    private byte[] documentFile;

    private Integer documentId;

    private String fileData;  // base64 encoded


}
