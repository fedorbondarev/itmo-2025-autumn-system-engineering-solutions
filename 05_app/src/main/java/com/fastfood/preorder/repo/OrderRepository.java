package com.fastfood.preorder.repo;

import com.fastfood.preorder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
