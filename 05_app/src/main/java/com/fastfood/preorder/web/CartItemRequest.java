package com.fastfood.preorder.web;

public class CartItemRequest {
  private Long dishId;
  private Integer qty;

  public Long getDishId() { return dishId; }
  public void setDishId(Long dishId) { this.dishId = dishId; }
  public Integer getQty() { return qty; }
  public void setQty(Integer qty) { this.qty = qty; }
}
