package com.alonsoestevam.springcourse.services;

import com.alonsoestevam.springcourse.entities.User;
import com.alonsoestevam.springcourse.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        return repository.findById(id).get();
    }
}
