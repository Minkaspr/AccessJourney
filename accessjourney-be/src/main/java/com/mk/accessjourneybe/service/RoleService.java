package com.mk.accessjourneybe.service;

import com.mk.accessjourneybe.entity.Role;

import java.util.List;

public interface RoleService {

    Role getRole(Long id);
    List<Role> getAllRoles();
    Role createRole(Role newRole);
    Role editRole(Long id, Role updateRole);
    void deleteRole(Long id);

}
