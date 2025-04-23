package com.bloggingplatform.BloggingPlatform.service;

import com.bloggingplatform.BloggingPlatform.dao.CategoryDao;
import com.bloggingplatform.BloggingPlatform.dto.CategoryDto;
import com.bloggingplatform.BloggingPlatform.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public List<CategoryDto> getAllCategories(){
        List<Category> categories = categoryDao.getAllCategories();
        return categories.stream()
                .map(category -> new CategoryDto(
                        category.getId(),
                        category.getName()
                )).collect(Collectors.toUnmodifiableList());
    }

    public CategoryDto getCategoryById(Long id){
        Category category = categoryDao.getCategoryById(id);
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }

    public Category createCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        categoryDao.creteCategory(category);
        return category;
    }

    public Category deleteCategory(Long id){
        Category category = categoryDao.getCategoryById(id);
        categoryDao.deleteCategory(id);
        return category;
    }
}
