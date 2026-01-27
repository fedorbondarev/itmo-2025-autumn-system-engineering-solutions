package com.fastfood.preorder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  @JsonIgnore
  private Order order;

  @ManyToOne
  @JoinColumn(name = "dish_id", nullable = false)
  @JsonIgnore
  private Dish dish;

  private int qty;
  private double priceAtOrder;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public Order getOrder() { return order; }
  public void setOrder(Order order) { this.order = order; }

  public Dish getDish() { return dish; }
  public void setDish(Dish dish) { this.dish = dish; }

  @JsonProperty("dishId")
  public Long getDishId() { return dish != null ? dish.getId() : null; }

  @JsonProperty("dishName")
  public String getDishName() { return dish != null ? dish.getName() : null; }

  public int getQty() { return qty; }
  public void setQty(int qty) { this.qty = qty; }
  public double getPriceAtOrder() { return priceAtOrder; }
  public void setPriceAtOrder(double priceAtOrder) { this.priceAtOrder = priceAtOrder; }
}
