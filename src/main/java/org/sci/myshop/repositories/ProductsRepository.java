package org.sci.myshop.repositories;

import org.sci.myshop.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByName(String name);

    @Query("SELECT p FROM Product p WHERE UPPER(CONCAT(p.name, ' ', p.category, ' ', p.description, ' ', p.manufacturer, ' ', p.price)) LIKE UPPER(CONCAT('%', ?1, '%')) ")
    List<Product> search(String keyword);

    @Query("SELECT p FROM Product p WHERE UPPER(CONCAT(p.category)) LIKE UPPER(CONCAT('%', ?1, '%')) ")
    List<Product> showProductsByCategory(String keyword);

}
