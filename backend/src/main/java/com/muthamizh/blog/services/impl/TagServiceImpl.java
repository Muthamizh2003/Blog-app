package com.muthamizh.blog.services.impl;

import com.muthamizh.blog.domain.entities.Tag;
import com.muthamizh.blog.repositories.TagRepository;
import com.muthamizh.blog.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAllWithPostCount();
    }

    @Transactional
    @Override
    public List<Tag> createTags(Set<String> tagNames) {
        List<Tag> existingTags=tagRepository.findByNameIn(tagNames);
        Set<String> existingTagNames=existingTags.stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());
        List<Tag> newTags=tagNames.stream()
                .filter(name->!existingTagNames.contains(name))
                .map(name->Tag.builder()
                        .name(name)
                        .posts(new HashSet<>())
                        .build())
                .toList();

        List<Tag> savedTags=new ArrayList<>();
        if(!newTags.isEmpty())
        {
            tagRepository.saveAll(newTags);
        }

        savedTags.addAll(existingTags);

        return savedTags;
    }

    @Transactional
    @Override
    public void deleteTag(UUID id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found with ID " + id));

        // Detach this tag from all associated posts
        tag.getPosts().forEach(post -> post.getTags().remove(tag));
        tag.getPosts().clear(); // Optional: clear for safety

        tagRepository.delete(tag);
    }

    @Override
    public Tag getTagById(UUID id) {
        return tagRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Tag not found with ID "+id));
    }

    @Override
    public List<Tag> getTagByIds(Set<UUID> ids) {
        List<Tag> foundTags=tagRepository.findAllById(ids);
        if(foundTags.size()!=ids.size())
        {
            throw new EntityNotFoundException("Not all specified tag IDs exist");
        }
        return foundTags;
    }
}
