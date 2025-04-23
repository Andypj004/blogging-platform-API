package com.bloggingplatform.BloggingPlatform.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class PostRequestDto {

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, message = "Title cannot be empty")
    private String title;
    @NotNull(message = "Content cannot be null")
    @Size(min = 1, message = "Content cannot be empty")
    private String content;
    @NotNull(message = "Category ID cannot be null")
    private String category;
    @NotNull(message = "Tags cannot be null")
    private List<String> tags;
}
