package com.alonsoestevam.springcourse.repositories;

import com.alonsoestevam.springcourse.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
