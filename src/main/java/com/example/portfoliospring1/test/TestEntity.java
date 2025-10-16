package com.example.portfoliospring1.test;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TestEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long count;

    String redis;
}