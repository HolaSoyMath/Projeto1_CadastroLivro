package com.matchola.projeto1.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record BookRecordDto(@NotBlank String title, @NotBlank String author, @NotBlank String isbn, @NotNull Date date_published) {
}