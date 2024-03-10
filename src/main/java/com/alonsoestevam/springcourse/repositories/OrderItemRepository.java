package com.alonsoestevam.springcourse.repositories;

import com.alonsoestevam.springcourse.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

// aqui o @Repository é opcional
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
