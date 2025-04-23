package com.bloggingplatform.BloggingPlatform.service;

import com.bloggingplatform.BloggingPlatform.dao.CategoryDao;
import com.bloggingplatform.BloggingPlatform.dao.PostDao;
import com.bloggingplatform.BloggingPlatform.dao.TagDao;
import com.bloggingplatform.BloggingPlatform.dto.PostDto;
import com.bloggingplatform.BloggingPlatform.dto.PostRequestDto;
import com.bloggingplatform.BloggingPlatform.model.Category;
import com.bloggingplatform.BloggingPlatform.model.Post;
import com.bloggingplatform.BloggingPlatform.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private TagDao tagDao;

    public List<PostDto> getAllPost(){
        List<Post> posts = postDao.getAllPost();
        return posts.stream()
                .map(post -> new PostDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCategory().getName(),
                        post.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()),
                        post.getCreatedAt(),
                        post.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    public PostDto getPostById(Long id){
        Post post = postDao.getPost(id);
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCategory().getName(),
                post.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

    public Post createPost(PostRequestDto postRequestDto) {
        Category category = categoryDao.getCategoryByName(postRequestDto.getCategory());

        List<Tag> tags = tagDao.getTagsByNames(postRequestDto.getTags());

        Post post = new Post();
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        post.setCategory(category);
        post.setTags(tags);
        post.setCreatedAt(LocalDate.now());
        post.setUpdatedAt(LocalDate.now());

        postDao.createPost(post);

        return post;
    }

    public Post updatePost(Long id, PostRequestDto postRequestDto){
        Post post = postDao.getPost(id);
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());

        Category category = categoryDao.getCategoryByName(postRequestDto.getCategory());
        post.setCategory(category);

        List<Tag> tags = tagDao.getTagsByNames(postRequestDto.getTags());
        post.setTags(tags);

        post.setUpdatedAt(LocalDate.now());
        postDao.updatePost(post);
        return post;
    }

    public Post deletePost(Long id){
        Post post = postDao.getPost(id);
        postDao.deletePost(id);
        return post;
    }

    public List<PostDto> getPostByTerm(String term){
        List<Post> posts = postDao.getPostByTerm(term);
        return posts.stream().map(post -> new PostDto(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getCategory().getName(),
                        post.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()),
                        post.getCreatedAt(),
                        post.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }
}
