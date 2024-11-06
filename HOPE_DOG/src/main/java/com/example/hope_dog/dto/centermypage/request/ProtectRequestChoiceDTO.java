package com.example.hope_dog.dto.centermypage.request;

import lombok.*;

@Data
public class ProtectRequestChoiceDTO {
    private Long protectRequestNo;
    private String protectRequestStatus;


    public ProtectRequestChoiceDTO(Long protectRequestNo, String protectRequestStatus) {
        this.protectRequestNo = protectRequestNo;
        this.protectRequestStatus = protectRequestStatus;
    }
}
