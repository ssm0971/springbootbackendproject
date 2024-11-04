package com.example.hope_dog.dto.commu;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter @Setter @ToString
@NoArgsConstructor
public class CommuDetailDTO extends CommuDTO {
    private List<CommuCommentDTO> comments;
}
