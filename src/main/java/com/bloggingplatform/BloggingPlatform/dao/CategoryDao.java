package com.bloggingplatform.BloggingPlatform.dao;

import com.bloggingplatform.BloggingPlatform.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Category> getAllCategories(){
        String query = "FROM Category";
        return entityManager.createQuery(query).getResultList();
    }

    public Category getCategoryById(Long id){
        return entityManager.find(Category.class, id);
    }

    public Category getCategoryByName(String name) {
        String jpql = "SELECT c FROM Category c WHERE c.name = :name";
        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);
        query.setParameter("name", name);

        return query.getResultStream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("Category not found"));
    }

    public void creteCategory(Category category){
            entityManager.persist(category);
    }

    public void deleteCategory(Long id){
        Category category = entityManager.find(Category.class, id);
        entityManager.remove(category);
    }
}
