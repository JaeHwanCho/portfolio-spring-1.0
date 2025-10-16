package com.example.portfoliospring1.repository;

import com.example.portfoliospring1.domain.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
