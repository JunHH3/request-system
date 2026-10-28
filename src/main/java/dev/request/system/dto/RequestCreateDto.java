package dev.request.system.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestCreateDto(
        @NotBlank String title,
        @NotBlank String content
) {
}
