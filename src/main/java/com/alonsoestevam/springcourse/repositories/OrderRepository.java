package com.alonsoestevam.springcourse.repositories;

import com.alonsoestevam.springcourse.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
