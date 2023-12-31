package com.rungroop.webmvc.dto;

import com.rungroop.webmvc.model.Club;
import com.rungroop.webmvc.validetor.ValidedEndTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ValidedEndTime
public class EventDto {
    private Long Id;
    @NotEmpty(message = "Event Name is mandatory")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Start Time is mandatory")
    @PastOrPresent(message = "Start Time should be in the past or present")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "End Time is mandatory")
    private LocalDateTime endTime;
    @NotEmpty(message = "Event Type is mandatory")
    private String type;
    @NotEmpty(message = "Photo should not be empty")
    private String photoUrl;


    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private Club club;
}
