package com.example.hope_dog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VerificationInfo {
    private String code;
    private long expirationTime;
}