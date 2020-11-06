package com.example.pokeIndex.controllers;


import com.example.pokeIndex.entities.User;
import com.example.pokeIndex.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Find a user with a username. if name is empty all pokemons from db will show up, need role Admin to access")
    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<User>> findAllUsersbyName(@RequestParam(required = false) String username) {
        var users = userService.findAll(username);
        return ResponseEntity.ok(users);
    }
    @Operation(summary = "Find a user with a id, need role Admin to access")
    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "Add a new user to the database, no authentication needed")
    @PostMapping
    public ResponseEntity<User> saveUser(@Validated @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @Operation(summary = "Update a user by id, users can only update themself and admin can change all users")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public void updateUser(@PathVariable String id, @Validated @RequestBody User user)
    {
        userService.update(id, user);
    }

    @Operation(summary = "Delete a user by id, need role Admin to access")
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }
}
