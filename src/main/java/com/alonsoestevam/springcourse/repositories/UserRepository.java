package com.alonsoestevam.springcourse.repositories;

import com.alonsoestevam.springcourse.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

// aqui o @Repository é opcional
public interface UserRepository extends JpaRepository<User, Long> {
}
