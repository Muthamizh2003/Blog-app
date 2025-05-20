package com.muthamizh.blog.services;

import com.muthamizh.blog.domain.entities.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
}
