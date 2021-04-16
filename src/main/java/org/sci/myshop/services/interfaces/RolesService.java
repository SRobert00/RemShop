package org.sci.myshop.services.interfaces;

import org.sci.myshop.dao.Role;

import java.util.List;

public interface RolesService {
    void saveRoles(List<Role> roles);
    List<Role> findAllRoles();
}
