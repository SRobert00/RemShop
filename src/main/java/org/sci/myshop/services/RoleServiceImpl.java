package org.sci.myshop.services;

import org.sci.myshop.dao.Role;
import org.sci.myshop.repositories.RoleRepository;
import org.sci.myshop.services.interfaces.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RolesService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void saveRoles(List<Role> roles) {
        for (Role r:roles) {
          roleRepository.save(r);
        }
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
