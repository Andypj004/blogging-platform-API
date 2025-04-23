package com.bloggingplatform.BloggingPlatform.service;

import com.bloggingplatform.BloggingPlatform.dao.TagDao;
import com.bloggingplatform.BloggingPlatform.dto.TagDto;
import com.bloggingplatform.BloggingPlatform.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    TagDao tagDao;

    public List<TagDto> getAllTags(){
        List<Tag> tags = tagDao.getAllTags();
        return tags.stream()
                .map(tag -> new TagDto(
                        tag.getId(),
                        tag.getName()
                )).collect(Collectors.toUnmodifiableList());
    }

    public TagDto getTagById(Long id){
        Tag tag = tagDao.getTagById(id);
        return new TagDto(
                tag.getId(),
                tag.getName()
        );
    }

    public Tag createTag(TagDto tagDto){
        Tag tag = new Tag();
        tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());
        tagDao.createTag(tag);
        return tag;
    }

    public Tag deleteTag(Long id){
        Tag tag = tagDao.getTagById(id);
        tagDao.deleteTag(id);
        return tag;
    }
}
