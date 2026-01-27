package com.fastfood.preorder.web;

import com.fastfood.preorder.model.Dish;
import com.fastfood.preorder.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dishes")
@Tag(name = "Menu")
public class MenuController {
  private final MenuService menuService;

  public MenuController(MenuService menuService) {
    this.menuService = menuService;
  }

  @GetMapping
  @Operation(summary = "Список блюд")
  public List<Dish> list() {
    return menuService.list();
  }
}
