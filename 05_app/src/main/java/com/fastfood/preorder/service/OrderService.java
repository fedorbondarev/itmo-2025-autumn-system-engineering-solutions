package com.fastfood.preorder.service;

import com.fastfood.preorder.model.Cart;
import com.fastfood.preorder.model.CartItem;
import com.fastfood.preorder.model.FFUser;
import com.fastfood.preorder.model.Order;
import com.fastfood.preorder.model.OrderItem;
import com.fastfood.preorder.model.OrderStatus;
import com.fastfood.preorder.repo.OrderRepository;
import com.fastfood.preorder.repo.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final CartService cartService;

  public OrderService(OrderRepository orderRepository, UserRepository userRepository, CartService cartService) {
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
    this.cartService = cartService;
  }

  public Order createOrder(long userId, LocalDateTime requestedTime) {
    Cart cart = cartService.getCart(userId);
    FFUser user = userRepository.findById(userId).orElseThrow();

    Order order = new Order();
    order.setUser(user);
    order.setStatus(OrderStatus.CREATED);
    order.setRequestedTime(requestedTime);
    order.setCreatedAt(LocalDateTime.now());

    double total = 0;
    for (CartItem item : cart.getItems()) {
      OrderItem orderItem = new OrderItem();
      orderItem.setOrder(order);
      orderItem.setDish(item.getDish());
      orderItem.setQty(item.getQty());
      orderItem.setPriceAtOrder(item.getDish().getPrice());
      order.getItems().add(orderItem);
      total += item.getQty() * item.getDish().getPrice();
    }
    order.setTotalAmount(total);

    Order saved = orderRepository.save(order);
    cartService.clear(userId);
    return saved;
  }

  public List<Order> listOrders() {
    return orderRepository.findAll();
  }

  public Order getOrder(long id) {
    return orderRepository.findById(id).orElse(null);
  }

  public Order updateStatus(long id, OrderStatus status) {
    Order order = orderRepository.findById(id).orElse(null);
    if (order != null) {
      order.setStatus(status);
      orderRepository.save(order);
    }
    return order;
  }
}
