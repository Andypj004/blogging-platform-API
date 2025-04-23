package com.bloggingplatform.BloggingPlatform.controller;

import com.bloggingplatform.BloggingPlatform.dto.PostDto;
import com.bloggingplatform.BloggingPlatform.dto.PostRequestDto;
import com.bloggingplatform.BloggingPlatform.model.Post;
import com.bloggingplatform.BloggingPlatform.service.PostService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getPosts(@RequestParam(required = false) String term) {
        List<PostDto> postDtos;
        if (term != null && !term.isEmpty()) {
            postDtos = postService.getPostByTerm(term);
        } else {
            postDtos = postService.getAllPost();
        }
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id){
        try {
            PostDto postDto = postService.getPostById(id);
            return ResponseEntity.ok(postDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequestDto postRequestDto) {
        try {
            Post post = postService.createPost(postRequestDto);

            return new ResponseEntity<>(post, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Error en la validación de categoría o etiquetas
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Error genérico
            return new ResponseEntity<>("Error while creating post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @Valid @RequestBody PostRequestDto postRequestDto) {
        try {
            Post updatedPost = postService.updatePost(id, postRequestDto);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating post", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
