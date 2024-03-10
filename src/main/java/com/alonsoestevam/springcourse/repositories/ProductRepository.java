package com.alonsoestevam.springcourse.repositories;

import com.alonsoestevam.springcourse.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
