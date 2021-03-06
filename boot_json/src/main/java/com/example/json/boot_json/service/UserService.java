package com.example.json.boot_json.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.example.json.boot_json.models.User;
import com.example.json.boot_json.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Repository
public class UserService {


    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(User user, Long id) {
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}


