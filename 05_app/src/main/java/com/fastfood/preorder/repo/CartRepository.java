package com.fastfood.preorder.repo;

import com.fastfood.preorder.model.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
  Optional<Cart> findByUser_Id(Long userId);
}
