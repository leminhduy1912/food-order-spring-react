package com.foodOrder.repository;

import com.foodOrder.model.Cart;
import com.foodOrder.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
public Cart findByCustomerId(Long userId);


}
