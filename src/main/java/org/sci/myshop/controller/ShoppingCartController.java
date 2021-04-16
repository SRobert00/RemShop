package org.sci.myshop.controller;

import org.sci.myshop.dao.Product;
import org.sci.myshop.dao.ShoppingCart;
import org.sci.myshop.dao.User;
import org.sci.myshop.services.ProductServiceImpl;
import org.sci.myshop.services.ShoppingCartServiceImpl;
import org.sci.myshop.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ShoppingCartServiceImpl shoppingCartService;



    @RequestMapping("/ShoppingCart")
    public String getShoppingCart(Model model, @AuthenticationPrincipal UserDetails currentUser,  @Param("keyword") String keyword){

        model.addAttribute("userForm", new User());
        List<ShoppingCart> listProducts = shoppingCartService.listAll(currentUser.getUsername());
        model.addAttribute("listProducts", listProducts);

        return "ShoppingCart";
    }

    @PostMapping("/ShoppingCart/deleteById/{id}")
    public String deleteProductById(@PathVariable Long id, Model model){
        shoppingCartService.deleteProduct(id);
        return "ShoppingCart";
    }
}
