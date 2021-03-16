package com.example.json.boot_json.service;

import com.example.json.boot_json.models.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    User saveUser(User user);

    User updateUser(User user);

    void deleteUserById(Long id);

    void deleteUser(User user);
}
