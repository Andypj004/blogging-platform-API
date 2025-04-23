package com.bloggingplatform.BloggingPlatform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @ManyToMany
    @JoinTable(name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @JsonManagedReference
    private List<Tag> tags;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Post{id=" + id + ", title='" + title + "', content='" + content + "'}";
    }

    @JsonProperty("category")
    public String getCategoryName() {
        return category != null ? category.getName() : null;
    }

    @JsonProperty("tags")
    public List<String> getTagNames() {
        return tags != null ? tags.stream().map(Tag::getName).collect(Collectors.toList()) : null;
    }
}
