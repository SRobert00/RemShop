package org.sci.myshop.controller;

import org.sci.myshop.dao.Product;
import org.sci.myshop.dao.User;
import org.sci.myshop.services.ProductServiceImpl;
import org.sci.myshop.services.UserServiceImpl;
import org.sci.myshop.services.interfaces.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductsController {

    @Autowired
    ShoppingCartService shoppingCartContentsService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private ProductServiceImpl productService;

    @RequestMapping("/ProductsManagement")
    public String getProductManagementPage(Model model, HttpServletRequest request){
        List<Product> listProducts = productService.findAllProducts();
        model.addAttribute("listProducts", listProducts);

        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);

        return "ProductsManagement";
    }

    @PostMapping("/ProductsManagement/AddToCart/{id}")
    public String addToCartProductManagement(@PathVariable Long id, @AuthenticationPrincipal UserDetails currentUser, HttpServletRequest request){
        String user = currentUser.getUsername();

        productService.findAndAddToCartById(id, user);
        return getPreviousPageByRequest(request).orElse("ProductsByCategory");
    }

    @PostMapping("/ProductsManagement/AddNewProduct")
    public String addNewProduct(@ModelAttribute("newProduct") Product newProduct, Model model, BindingResult bindingResult){

        model.addAttribute("newProduct", newProduct);

        productService.save(newProduct);

        return "redirect:/ProductsManagement";
    }

    @PostMapping("/ProductsManagement/DeleteById/{id}")
    public String deleteProductById(@PathVariable Long id, Model model){
        productService.deleteProductById(id);
        return "redirect:/ProductsManagement";
    }


    @RequestMapping("/CarParts")
    public String getCarParts(Model model){

        model.addAttribute("userForm", new User());
        List<Product> listProducts = productService.listAllByCategory("Car Parts");
        model.addAttribute("listProducts", listProducts);
        return "ProductsByCategory";
    }

    protected Optional<String> getPreviousPageByRequest(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }

    @PostMapping("/CarParts/{id}")
    public String addToCartById(@PathVariable Long id, @AuthenticationPrincipal UserDetails currentUser, HttpServletRequest request){
        String user = currentUser.getUsername();

        productService.findAndAddToCartById(id, user);
        return getPreviousPageByRequest(request).orElse("ProductsByCategory");
    }

    @RequestMapping("/Batteries")
        public String getBatteries(Model model){
        List<Product> listProducts = productService.listAllByCategory("Batteries");
        model.addAttribute("listProducts", listProducts);
        return "ProductsByCategory";
    }

    @RequestMapping("/Oil")
        public String getOil(Model model){
        List<Product> listProducts = productService.listAllByCategory("Oil");
        model.addAttribute("listProducts", listProducts);
        return "ProductsByCategory";
    }

    @RequestMapping("/Tires")
        public String getTires(Model model){
        List<Product> listProducts = productService.listAllByCategory("Tires");
        model.addAttribute("listProducts", listProducts);
        return "ProductsByCategory";
    }

    @RequestMapping("/Sensors")
        public String getSensors(Model model){
        List<Product> listProducts = productService.listAllByCategory("Sensors");
        model.addAttribute("listProducts", listProducts);
        return "ProductsByCategory";
    }

    @RequestMapping("/Tools")
    public String getTools(Model model){
        List<Product> listProducts = productService.listAllByCategory("Tools");
        model.addAttribute("listProducts", listProducts);
        return "ProductsByCategory";
    }
}
