package com.rungroop.webmvc.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder

public class ClubDto {

    private Long Id;
    @NotEmpty(message = "title is mandatory")
    private String title;
    @NotEmpty(message = "Photo Url Link should not be empty")
    private String photoUrl;
    @NotEmpty(message = "Content should not be empty")
    private String content;
    private LocalDateTime createdOn;
    private  LocalDateTime updatedOn;

    private List<EventDto> events;



}
