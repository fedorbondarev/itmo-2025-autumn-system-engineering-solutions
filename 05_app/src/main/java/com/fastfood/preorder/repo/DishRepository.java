package com.fastfood.preorder.repo;

import com.fastfood.preorder.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {}
