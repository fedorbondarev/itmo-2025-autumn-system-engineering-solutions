package com.fastfood.preorder.repo;

import com.fastfood.preorder.model.FFUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<FFUser, Long> {}
