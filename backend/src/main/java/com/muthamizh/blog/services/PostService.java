package com.muthamizh.blog.services;

import com.muthamizh.blog.domain.CreatePostRequest;
import com.muthamizh.blog.domain.UpdatePostRequest;
import com.muthamizh.blog.domain.entities.Post;
import com.muthamizh.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post getPost(UUID id);
    void deletePost(UUID id);
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
}