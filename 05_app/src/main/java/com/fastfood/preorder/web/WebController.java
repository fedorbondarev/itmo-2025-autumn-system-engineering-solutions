package com.fastfood.preorder.web;

import com.fastfood.preorder.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
  private final MenuService menuService;

  public WebController(MenuService menuService) {
    this.menuService = menuService;
  }

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("dishes", menuService.list());
    model.addAttribute("userId", 1);
    return "index";
  }

  @GetMapping("/staff")
  public String staff() {
    return "staff";
  }
}
