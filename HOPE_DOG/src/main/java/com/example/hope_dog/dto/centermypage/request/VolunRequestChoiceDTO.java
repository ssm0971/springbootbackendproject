package com.example.hope_dog.dto.centermypage.request;

import lombok.Data;

@Data
public class VolunRequestChoiceDTO {
    private Long volunRequestNo;
    private String volunRequestStatus;


    public VolunRequestChoiceDTO(Long volunRequestNo, String volunRequestStatus) {
        this.volunRequestNo = volunRequestNo;
        this.volunRequestStatus = volunRequestStatus;
    }
}
