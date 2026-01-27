package com.fastfood.preorder.web;

import com.fastfood.preorder.model.Order;
import com.fastfood.preorder.model.OrderStatus;
import com.fastfood.preorder.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Orders")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  @Operation(summary = "Создать заказ")
  public Order create(
      @Parameter(description = "ID пользователя") @RequestParam(value = "userId", required = false) Long userId,
      @Parameter(description = "Желаемое время выдачи (ISO)") @RequestParam(value = "requestedTime", required = false) String requestedTime,
      @RequestBody(required = false) OrderRequest body) {
    Long resolvedUserId = userId != null ? userId : body != null ? body.getUserId() : null;
    String resolvedTime = requestedTime != null ? requestedTime : body != null ? body.getRequestedTime() : null;
    if (resolvedUserId == null || resolvedTime == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId and requestedTime are required");
    }
    LocalDateTime time = LocalDateTime.parse(resolvedTime);
    return orderService.createOrder(resolvedUserId, time);
  }

  @GetMapping
  @Operation(summary = "Список заказов")
  public List<Order> list() {
    return orderService.listOrders();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Детали заказа")
  public Order get(@Parameter(description = "ID заказа") @PathVariable("id") long id) {
    return orderService.getOrder(id);
  }

  @PatchMapping("/{id}/status")
  @Operation(summary = "Изменить статус заказа")
  public Order updateStatus(
      @Parameter(description = "ID заказа") @PathVariable("id") long id,
      @Parameter(description = "Статус") @RequestParam(value = "status", required = false) String status,
      @RequestBody(required = false) StatusRequest body) {
    String resolved = status != null ? status : body != null ? body.getStatus() : null;
    if (resolved == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "status is required");
    }
    OrderStatus parsed = OrderStatus.valueOf(resolved.toUpperCase());
    return orderService.updateStatus(id, parsed);
  }
}
