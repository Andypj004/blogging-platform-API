package com.bloggingplatform.BloggingPlatform.dao;


import com.bloggingplatform.BloggingPlatform.model.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class PostDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Post> getAllPost() {
        String query = "SELECT p FROM Post p JOIN FETCH p.tags t";
        return entityManager.createQuery(query, Post.class).getResultList();
    }

    public Post getPost(Long id){
        return entityManager.find(Post.class, id);
    }

    public void createPost(Post post) {
        entityManager.persist(post);
    }

    public void updatePost(Post post) {
        entityManager.merge(post);
    }

    public void deletePost(Long id) {
        Post post = entityManager.find(Post.class, id);
        entityManager.remove(post);
    }

    public List<Post> getPostByTerm(String term){
        StringBuilder query = new StringBuilder("SELECT DISTINCT p FROM Post p LEFT JOIN FETCH p.tags t LEFT JOIN FETCH p.category c");

        if (term != null && !term.isEmpty()) {
            query.append(" WHERE LOWER(p.title) LIKE :term OR LOWER(p.content) LIKE :term OR LOWER(c.name) LIKE :term");
        }

        TypedQuery<Post> typedQuery = entityManager.createQuery(query.toString(), Post.class);

        if (term != null && !term.isEmpty()) {
            typedQuery.setParameter("term", "%" + term.toLowerCase() + "%");
        }

        return typedQuery.getResultList();
    }

}
