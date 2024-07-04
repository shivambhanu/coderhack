package com.backend.coderhack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PostUserRequestDto {
    private final String userId;
    private final String userName;
}
