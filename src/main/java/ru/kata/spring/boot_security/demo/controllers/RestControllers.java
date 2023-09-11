package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

@RestController
@RequestMapping("/api/users")
public class RestControllers {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public RestControllers(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable long userId) {
        return userService.getUserById(userId);
    }


    @PostMapping( "/{userId}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("userId") Long id, @RequestBody User user,
                                                 @RequestParam("users_roles") String[] selectedRoles) {
        userService.update(id, user, selectedRoles);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }



}