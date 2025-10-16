package com.example.portfoliospring1.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateInquiryDto {
    String nickname;
    String title;
    String content;
}
