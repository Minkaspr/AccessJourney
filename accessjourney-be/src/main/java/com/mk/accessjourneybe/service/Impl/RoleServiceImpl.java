package com.mk.accessjourneybe.service.Impl;

import com.mk.accessjourneybe.entity.Role;
import com.mk.accessjourneybe.repository.RoleRepository;
import com.mk.accessjourneybe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getRole(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.get();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role newRole) {
        return roleRepository.save(newRole);
    }

    @Override
    public Role editRole(Long id, Role updateRole) {
        // Encuentra el rol existente
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        // Actualiza los campos del rol existente
        existingRole.setName(updateRole.getName());
        existingRole.setDescription(updateRole.getDescription());

        // Guarda y devuelve el rol actualizado
        return roleRepository.save(existingRole);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
