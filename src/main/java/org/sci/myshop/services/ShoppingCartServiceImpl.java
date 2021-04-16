package org.sci.myshop.services;

import org.sci.myshop.dao.Product;
import org.sci.myshop.dao.ShoppingCart;
import org.sci.myshop.repositories.ProductsRepository;
import org.sci.myshop.repositories.ShoppingCartRepository;
import org.sci.myshop.services.interfaces.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired

    ProductsRepository productsRepository;


    //Use this to show contents of the cart with thymeleaf by getting the user's shopping cart id.
    public void findCartById(Long id) {
        shoppingCartRepository.findById(id);
    }

    @Override
    public void save(ShoppingCart product) {
        if(product != null) {
            shoppingCartRepository.save(product);
        }
    }

    public List<ShoppingCart> listAll(String keyword) {
        if (keyword != null) {
            return shoppingCartRepository.search(keyword);
        }
        return shoppingCartRepository.findAll();
    }

    public void deleteProduct(Long id) {
        shoppingCartRepository.deleteById(id);
    }
}
