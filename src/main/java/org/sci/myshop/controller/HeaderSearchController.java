package org.sci.myshop.controller;

import org.sci.myshop.dao.Product;
import org.sci.myshop.services.ProductServiceImpl;
import org.sci.myshop.services.UserServiceImpl;
import org.sci.myshop.services.interfaces.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class HeaderSearchController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    ShoppingCartService shoppingCartContentsService;

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/SearchResults")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Product> listProducts = productService.listAll(keyword);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("keyword", keyword);

        return "SearchResults";
    }

    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request)
    {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }

    @PostMapping("/SearchResults/{id}")
    public String addToCartById(@PathVariable Long id, @AuthenticationPrincipal UserDetails currentUser, HttpServletRequest request){
        String user = currentUser.getUsername();

        productService.findAndAddToCartById(id, user);
        return getPreviousPageByRequest(request).orElse("ProductsByCategory");
    }


    @GetMapping("/search")
    public String getSearch(Model model){

        List<Product> list = productService.findAllProducts();
        model.addAttribute("listOfProducts", list);
        return "search";
    }
}