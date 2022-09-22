package com.example.seminarksa_wp.repository;

import com.example.seminarksa_wp.model.ShoppingCart;
import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);

}
