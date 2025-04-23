package com.bloggingplatform.BloggingPlatform.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String category;
    private List<String> tags;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public PostDto(Long id, String title, String content, String category, List<String> tags, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.tags = tags;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
