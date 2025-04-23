package com.bloggingplatform.BloggingPlatform.controller;

import com.bloggingplatform.BloggingPlatform.dto.CategoryDto;

import com.bloggingplatform.BloggingPlatform.model.Category;
import com.bloggingplatform.BloggingPlatform.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtos =  categoryService.getAllCategories();
        return ResponseEntity.ok(categoryDtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id){
        try{
            CategoryDto categoryDto = categoryService.getCategoryById(id);
            return ResponseEntity.ok(categoryDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
        }

    }

    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody CategoryDto categoryDto){
        try {
            Category category = categoryService.createCategory(categoryDto);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Error en la validación de categoría o etiquetas
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
