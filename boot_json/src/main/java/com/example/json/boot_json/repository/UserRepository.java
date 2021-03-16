package com.example.json.boot_json.repository;

;
import com.example.json.boot_json.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    void deleteById(Long id);
}
