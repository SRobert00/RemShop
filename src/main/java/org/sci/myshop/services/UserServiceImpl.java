package org.sci.myshop.services;

import org.sci.myshop.dao.Role;
import org.sci.myshop.dao.User;
import org.sci.myshop.repositories.RoleRepository;
import org.sci.myshop.repositories.UserRepository;
import org.sci.myshop.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(user.getRole()==null||user.getRole().getName().isBlank()) {
            List<Role> roles = roleRepository.findAll();
            user.setRole(roles.get(0x1));
        }
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void findUserById(Long id){ userRepository.findById(id);}

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User oldUser, User newUser) {
        if(newUser.getUsername() != null) {
            oldUser.setUsername(newUser.getUsername());
        }
        if(newUser.getFullName() != null) {
            oldUser.setFullName(newUser.getFullName());
        }
        if(newUser.getAddress() != null) {
            oldUser.setAddress(newUser.getAddress());
        }
        if(newUser.getEmail() != null) {
            oldUser.setEmail(newUser.getEmail());
        }
        if(newUser.getRole() != null) {
            oldUser.setRole(newUser.getRole());
        }
        userRepository.save(oldUser);
    }
}
