package com.bloggingplatform.BloggingPlatform.dao;

import com.bloggingplatform.BloggingPlatform.model.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TagDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Tag> getAllTags(){
        String query = "FROM Tag";
        return entityManager.createQuery(query).getResultList();
    }

    public Tag getTagById(Long id){
        return entityManager.find(Tag.class, id);
    }

    public List<Tag> getTagsByNames(List<String> names) {
        String jpql = "SELECT t FROM Tag t WHERE t.name IN :names";
        TypedQuery<Tag> query = entityManager.createQuery(jpql, Tag.class);
        query.setParameter("names", names);

        List<Tag> tags = query.getResultList();

        if (tags.size() != names.size()) {
            throw new IllegalArgumentException("One or more tags not found");
        }
        return tags;
    }

    public void createTag(Tag tag){
        entityManager.persist(tag);
    }

    public void deleteTag(Long id){
        Tag tag = entityManager.find(Tag.class, id);
        entityManager.remove(tag);
    }
}
