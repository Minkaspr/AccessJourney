package com.mk.accessjourneybe.service.Impl;

import com.mk.accessjourneybe.entity.Role;
import com.mk.accessjourneybe.repository.RoleRepository;
import com.mk.accessjourneybe.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
        return role;
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
        Role existingRole = getRole(id);

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
