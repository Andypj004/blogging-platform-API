package com.bloggingplatform.BloggingPlatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Post> posts;

    @Override
    public String toString() {
        return "Category{id=" + id + ", name='" + name + "'}";
    }
}
