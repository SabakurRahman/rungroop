package com.rungroop.webmvc.validetor;

import com.rungroop.webmvc.dto.EventDto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;


public class EndTimeAfterStartTimeValidator implements ConstraintValidator<ValidedEndTime, EventDto> {
    @Override
    public boolean isValid(EventDto event, ConstraintValidatorContext constraintValidatorContext) {
        if (event == null || event.getStartTime() == null || event.getEndTime() == null) {
            return true; // Let other constraints handle null values
        }

        LocalDateTime startTime = event.getStartTime();
        LocalDateTime endTime = event.getEndTime();

        return endTime.isAfter(startTime);
    }
    }

