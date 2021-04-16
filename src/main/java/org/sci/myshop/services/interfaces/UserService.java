package org.sci.myshop.services.interfaces;

import org.sci.myshop.dao.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    List<User> findAllUsers();
}
