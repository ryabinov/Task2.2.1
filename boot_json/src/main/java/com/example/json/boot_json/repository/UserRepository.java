package com.example.json.boot_json.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.json.boot_json.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByname(String name);
}
