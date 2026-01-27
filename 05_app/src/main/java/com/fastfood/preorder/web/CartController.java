package com.fastfood.preorder.web;

import com.fastfood.preorder.model.Cart;
import com.fastfood.preorder.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart")
public class CartController {
  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping("/{userId}")
  @Operation(summary = "Получить корзину пользователя")
  public Cart getCart(@Parameter(description = "ID пользователя") @PathVariable("userId") long userId) {
    return cartService.getCart(userId);
  }

  @PostMapping("/{userId}/items")
  @Operation(summary = "Добавить блюдо в корзину")
  public void addItem(
      @Parameter(description = "ID пользователя") @PathVariable("userId") long userId,
      @Parameter(description = "ID блюда") @RequestParam(value = "dishId", required = false) Long dishId,
      @Parameter(description = "Количество") @RequestParam(value = "qty", required = false) Integer qty,
      @RequestBody(required = false) CartItemRequest body) {
    Long resolvedDishId = dishId != null ? dishId : body != null ? body.getDishId() : null;
    Integer resolvedQty = qty != null ? qty : body != null ? body.getQty() : null;
    if (resolvedDishId == null || resolvedQty == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "dishId and qty are required");
    }
    cartService.addItem(userId, resolvedDishId, resolvedQty);
  }

  @PutMapping("/{userId}/items")
  @Operation(summary = "Изменить количество блюда в корзине")
  public void updateItem(
      @Parameter(description = "ID пользователя") @PathVariable("userId") long userId,
      @Parameter(description = "ID блюда") @RequestParam(value = "dishId", required = false) Long dishId,
      @Parameter(description = "Количество") @RequestParam(value = "qty", required = false) Integer qty,
      @RequestBody(required = false) CartItemRequest body) {
    Long resolvedDishId = dishId != null ? dishId : body != null ? body.getDishId() : null;
    Integer resolvedQty = qty != null ? qty : body != null ? body.getQty() : null;
    if (resolvedDishId == null || resolvedQty == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "dishId and qty are required");
    }
    cartService.updateItem(userId, resolvedDishId, resolvedQty);
  }
}
