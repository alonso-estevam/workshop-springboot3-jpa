package com.alonsoestevam.springcourse.services;

import com.alonsoestevam.springcourse.entities.User;
import com.alonsoestevam.springcourse.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    // o método findById vai até o banco de dados e traz o objeto
    public User findById(Long id){
        return repository.findById(id).get();
    }

    public User insert(User user){
        return repository.save(user);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public User update(Long id, User dataFromRequest){
        // getReferenceById é mais eficiente nesse caso do que o findById
        User dataFromDataBase = repository.getReferenceById(id);
        updateData(dataFromDataBase, dataFromRequest);
        return repository.save(dataFromDataBase);
    }

    private void updateData(User dataFromDataBase, User dataFromRequest){
        dataFromDataBase.setName(dataFromRequest.getName());
        dataFromDataBase.setEmail(dataFromRequest.getEmail());
        dataFromDataBase.setPhone(dataFromRequest.getPhone());
    }
}
