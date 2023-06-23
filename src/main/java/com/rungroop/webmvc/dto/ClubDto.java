package com.rungroop.webmvc.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Builder

public class ClubDto {

    private Long Id;
    private String title;
    private String photoUrl;
    private String content;
    private LocalDateTime createdOn;
    private  LocalDateTime updatedOn;

}
