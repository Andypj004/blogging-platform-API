package com.bloggingplatform.BloggingPlatform.controller;

import com.bloggingplatform.BloggingPlatform.dto.TagDto;
import com.bloggingplatform.BloggingPlatform.model.Tag;
import com.bloggingplatform.BloggingPlatform.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping
    public List<TagDto> getAllTags(){
        return tagService.getAllTags();
    }

    @GetMapping(value = "/{id}")
    public TagDto getTagById(@PathVariable Long id){
        return tagService.getTagById(id);
    }

    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody TagDto tagDto){
        try {
            Tag tag = tagService.createTag(tagDto);
            return new ResponseEntity<>(tag, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error while creating tag", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable Long id){
        tagService.deleteTag(id);
        return ResponseEntity.ok("Tag deleted successfully");
    }

}
