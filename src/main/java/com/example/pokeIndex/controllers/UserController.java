package com.example.pokeIndex.controllers;


import com.example.pokeIndex.entities.User;
import com.example.pokeIndex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping // ?username=
    public ResponseEntity<List<User>> findAllUsers(@RequestParam(required = false) String username) {
        var users = userService.findAll(username);
        // return new ResponseEntity<>(users, HttpStatus.OK); // 200
        // return ResponseEntity.status(HttpStatus.OK);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}") // /api/v1/users/xxxxxxxxxxxx
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable String id,  @RequestBody User user) {
        userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }
}
