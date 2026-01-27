package com.fastfood.preorder.web;

public class OrderRequest {
  private Long userId;
  private String requestedTime;

  public Long getUserId() { return userId; }
  public void setUserId(Long userId) { this.userId = userId; }
  public String getRequestedTime() { return requestedTime; }
  public void setRequestedTime(String requestedTime) { this.requestedTime = requestedTime; }
}
