package org.sci.myshop.services;

import org.sci.myshop.dao.Product;
import org.sci.myshop.dao.ShoppingCart;
import org.sci.myshop.repositories.ProductsRepository;
import org.sci.myshop.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    ShoppingCartServiceImpl shoppingCartService;

    @Override
    public void save(Product product) {
        productsRepository.save(product);
    }

    @Override
    public List<Product> findByName(String name) {
        return productsRepository.findAllByName(name);
    }

    @Override
    public List<Product> findAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public void findAndAddToCartById(Long id, String username) {
        Optional<Product> myProduct = productsRepository.findById(id);

        //using Shopping Cart Service to save object from optional
        if(myProduct.isPresent()){
            ShoppingCart newProduct = new ShoppingCart();
            newProduct.setBelongsToUser(username);
            newProduct.setProductId(myProduct.get().getProductId());
            newProduct.setName(myProduct.get().getName());
            newProduct.setCategory(myProduct.get().getCategory());
            newProduct.setDescription(myProduct.get().getDescription());
            newProduct.setManufacturer(myProduct.get().getManufacturer());
            newProduct.setPictureLocation(myProduct.get().getPictureLocation());
            newProduct.setPrice(myProduct.get().getPrice());

            shoppingCartService.save(newProduct);
        }
    }

    @Override
    public void deleteProductById(long id) {
        productsRepository.deleteById(id);
    }


    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return productsRepository.search(keyword);
        }
        return productsRepository.findAll();
    }

    public List<Product> listAllByCategory(String keyword) {
        if (keyword != null) {
            return productsRepository.search(keyword);
        }
        return productsRepository.findAll();
    }
}
