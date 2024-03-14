package com.alonsoestevam.springcourse.services;

import com.alonsoestevam.springcourse.entities.User;
import com.alonsoestevam.springcourse.repositories.UserRepository;
import com.alonsoestevam.springcourse.services.exceptions.DatabaseException;
import com.alonsoestevam.springcourse.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User user){
        return repository.save(user);
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
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
