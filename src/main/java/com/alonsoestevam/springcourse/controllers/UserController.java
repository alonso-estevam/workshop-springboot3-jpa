package com.alonsoestevam.springcourse.controllers;

import com.alonsoestevam.springcourse.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @GetMapping
    public ResponseEntity<User> getUsers(){
        User u = new User(1L, "Aline", "liny@email.com", "33841549", "1234");
        return ResponseEntity.ok().body(u);
    }
}
