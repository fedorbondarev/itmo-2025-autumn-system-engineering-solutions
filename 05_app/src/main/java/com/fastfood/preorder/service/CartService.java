package com.fastfood.preorder.service;

import com.fastfood.preorder.model.Cart;
import com.fastfood.preorder.model.CartItem;
import com.fastfood.preorder.model.Dish;
import com.fastfood.preorder.model.FFUser;
import com.fastfood.preorder.repo.CartRepository;
import com.fastfood.preorder.repo.DishRepository;
import com.fastfood.preorder.repo.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CartService {
  private final CartRepository cartRepository;
  private final UserRepository userRepository;
  private final DishRepository dishRepository;

  public CartService(CartRepository cartRepository, UserRepository userRepository, DishRepository dishRepository) {
    this.cartRepository = cartRepository;
    this.userRepository = userRepository;
    this.dishRepository = dishRepository;
  }

  public Cart getCart(long userId) {
    Optional<Cart> existing = cartRepository.findByUser_Id(userId);
    if (existing.isPresent()) {
      return existing.get();
    }
    FFUser user = userRepository.findById(userId).orElseThrow();
    Cart cart = new Cart();
    cart.setUser(user);
    cart.setUpdatedAt(LocalDateTime.now());
    return cartRepository.save(cart);
  }

  public void addItem(long userId, long dishId, int qty) {
    Cart cart = getCart(userId);
    Dish dish = dishRepository.findById(dishId).orElseThrow();
    for (CartItem item : cart.getItems()) {
      if (item.getDish().getId().equals(dishId)) {
        item.setQty(item.getQty() + qty);
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
        return;
      }
    }
    CartItem item = new CartItem();
    item.setCart(cart);
    item.setDish(dish);
    item.setQty(qty);
    cart.getItems().add(item);
    cart.setUpdatedAt(LocalDateTime.now());
    cartRepository.save(cart);
  }

  public void updateItem(long userId, long dishId, int qty) {
    Cart cart = getCart(userId);
    CartItem target = null;
    for (CartItem item : cart.getItems()) {
      if (item.getDish().getId().equals(dishId)) {
        target = item;
        break;
      }
    }
    if (target == null && qty > 0) {
      Dish dish = dishRepository.findById(dishId).orElseThrow();
      CartItem item = new CartItem();
      item.setCart(cart);
      item.setDish(dish);
      item.setQty(qty);
      cart.getItems().add(item);
      cart.setUpdatedAt(LocalDateTime.now());
      cartRepository.save(cart);
      return;
    }
    if (target != null) {
      if (qty <= 0) {
        cart.getItems().remove(target);
      } else {
        target.setQty(qty);
      }
      cart.setUpdatedAt(LocalDateTime.now());
      cartRepository.save(cart);
    }
  }

  public void clear(long userId) {
    Cart cart = getCart(userId);
    cart.getItems().clear();
    cart.setUpdatedAt(LocalDateTime.now());
    cartRepository.save(cart);
  }
}
