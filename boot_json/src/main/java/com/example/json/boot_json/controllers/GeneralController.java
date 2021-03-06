package com.example.json.boot_json.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.json.boot_json.models.User;
import com.example.json.boot_json.service.UserService;


import java.util.List;

@RestController
@RequestMapping(value = "/listusers")
public class GeneralController {


    private final UserService userService;
    @Autowired
    public GeneralController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        final List<User> users = userService.allUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(name = "id") Long id) {
        final User user = userService.getById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable(name = "id") Long id){
        userService.update(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        User user = userService.getById(id);
        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
