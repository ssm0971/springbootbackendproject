package com.example.hope_dog.dto.centermypage.request;

import lombok.Data;

@Data
public class AdoptRequestChoiceDTO {
    private Long adoptRequestNo;
    private String adoptRequestStatus;


    public AdoptRequestChoiceDTO(Long adoptRequestNo, String adoptRequestStatus) {
        this.adoptRequestNo = adoptRequestNo;
        this.adoptRequestStatus = adoptRequestStatus;
    }
}
