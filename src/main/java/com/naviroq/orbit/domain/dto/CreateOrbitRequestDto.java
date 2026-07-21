package com.naviroq.orbit.domain.dto;

import com.naviroq.orbit.domain.entity.OrbitPriority;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

// this dto will be used to validate the api request and the structure expected from the API call upon POST request
public record CreateOrbitRequestDto(
        @NotBlank(message = ERR_TITLE_BLANK)
        @Length(max = 255, message = ERR_TITLE_LENGTH)
        String title,

        @Nullable
        @Length(max = 1000, message = ERR_DESCRIPTION_LENGTH)
        String description,

        @FutureOrPresent(message = ERR_DUE_DATE_MESSAGE)
        @Nullable
        LocalDate dueDate,

        @NotNull(message = ERR_PRIORITY_MESSAGE)
        OrbitPriority priority
) {
    private static final String ERR_TITLE_BLANK = "Title can not be blank";
    private static final String ERR_TITLE_LENGTH = "Title should be between 1 - 255 characters";
    private static final String ERR_DESCRIPTION_LENGTH = "Description should be between 1 - 1000 characters";
    private static final String ERR_DUE_DATE_MESSAGE = "Due date should be in the future";
    private static final String ERR_PRIORITY_MESSAGE = "Priority should be provided --HIGH or LOW or MEDIUM";
}
