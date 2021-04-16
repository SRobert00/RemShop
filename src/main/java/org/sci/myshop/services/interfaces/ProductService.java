package org.sci.myshop.services.interfaces;

import org.sci.myshop.dao.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void save(Product product);

    List<Product> findByName(String name);


    List<Product> findAllProducts();

    void findAndAddToCartById(Long id, String username);

    void deleteProductById(long id);
}
