package com.spring.jwt.Interest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SendInterestRequestDTO {

    @NotNull
    private Integer toUserId;
    private String message;
}
