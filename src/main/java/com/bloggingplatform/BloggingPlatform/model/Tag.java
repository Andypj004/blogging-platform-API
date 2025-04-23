package com.bloggingplatform.BloggingPlatform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tags")
@Data
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    @JsonManagedReference
    @JsonIgnore
    private List<Post> posts;

    @Override
    public String toString() {
        return "Tag{id=" + id + ", name='" + name + "'}";
    }
}
