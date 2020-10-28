package com.example.pokeIndex.services;

import com.example.pokeIndex.entities.User;
import com.example.pokeIndex.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired MyUserDetailsService myUserDetailsService;

    public List<User> findAll(String username) {
        if(username != null) {
            var user = userRepo.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Could not find the user by username %s.", username)));
            return List.of(user);
        }
        return userRepo.findAll();
    }

    public User findById(String id) {
        return userRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Could not find the user by id %s.", id)));
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(RuntimeException::new);
    }

    public User save(User user) {
        if(StringUtils.isEmpty(user.getPassword())) { // user.getPassword() == null || user.getPassword().isEmpty()
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "I need a password!!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public void update(String id, User user) {
        if(!userRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Could not find the user by id %s.", id));
        }
        var currentUserName = myUserDetailsService.getCurrentUser();
        System.out.println(currentUserName);
        if(!myUserDetailsService.checkUserRole("ADMIN")) {
            System.out.println(myUserDetailsService.checkUserRole("ADMIN"));
            System.out.println("not admin");
            if (!currentUserName.equals(user.getUsername())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("put is not allowed on other users and you cant change username"));
            }
        }

          /* var currentUser = userRepo.findByUsername(currentUserName)
                   .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(" user not found")));
           if(currentUser.getRoles().contains("ADMIN")){
               System.out.println("user is admin");
           }else {

           };

           */

        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public void delete(String id) {
        if(!userRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Could not find the user by id %s.", id));
        }
        userRepo.deleteById(id);
    }
}
