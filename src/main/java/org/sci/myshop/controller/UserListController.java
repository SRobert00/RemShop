package org.sci.myshop.controller;

import org.sci.myshop.dao.User;
import org.sci.myshop.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserListController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired UserDetailsService userDetailsService;

    @GetMapping("/UsersManagement")
    public String getUserList(Model model){
        List<User> list = userService.findAllUsers();
        model.addAttribute("listOfUsers",list);

        return "UsersManagement";
    }

    @PostMapping("/UsersManagement/{id}")
    public String deleteUserById(@PathVariable Long id){
       userService.deleteUser(id);
       return "redirect:/UsersManagement";
    }
}
