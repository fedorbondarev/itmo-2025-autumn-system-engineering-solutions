package com.fastfood.preorder.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "FastFood Preorder API",
    version = "1.0",
    description = "REST API for menu, cart, and orders"
  )
)
public class OpenApiConfig {}
