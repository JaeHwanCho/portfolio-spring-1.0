package com.example.portfoliospring1.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddUserDto {
    private String nickname;
    private String email;
    private String password;
}
