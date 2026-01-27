package com.fastfood.preorder.service;

import com.fastfood.preorder.model.Dish;
import com.fastfood.preorder.repo.DishRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
  private final DishRepository dishRepository;

  public MenuService(DishRepository dishRepository) {
    this.dishRepository = dishRepository;
  }

  public List<Dish> list() {
    return dishRepository.findAll();
  }

  public Optional<Dish> find(long id) {
    return dishRepository.findById(id);
  }
}
