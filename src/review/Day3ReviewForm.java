package dev.request.system.review;

import jakarta.validation.constraints.NotBlank;

public class Day3ReviewForm {

    @NotBlank
    String title;

    @NotBlank
    String content;

}
