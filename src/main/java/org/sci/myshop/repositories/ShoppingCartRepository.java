package org.sci.myshop.repositories;

import org.sci.myshop.dao.Product;
import org.sci.myshop.dao.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository <ShoppingCart, Long> {
    @Query("SELECT p FROM ShoppingCart p WHERE UPPER(CONCAT(p.user)) LIKE UPPER(CONCAT('%', ?1, '%')) ")
    List<ShoppingCart> search(String keyword);
}
