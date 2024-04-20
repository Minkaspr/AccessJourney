package com.mk.accessjourneybe.repository;

import com.mk.accessjourneybe.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testSaveRole() {
        // Crear rol
        Role clientRole = new Role();
        clientRole.setName("Client");
        clientRole.setDescription("Cliente del sistema");

        // Guardar rol
        roleRepository.save(clientRole);

        // Verificar que el rol se guardó correctamente
        assertNotNull(clientRole.getRoleId());
    }

    @Test
    void testSaveRoles() {
        // Crear roles
        Role adminRole = new Role();
        adminRole.setName("Admin");
        adminRole.setDescription("Acceso total al sistema");

        Role contractOfficerRole = new Role();
        contractOfficerRole.setName("ContractOfficer");
        contractOfficerRole.setDescription("Gestiona contratos y servicios");

        Role billingClerkRole = new Role();
        billingClerkRole.setName("BillingClerk");
        billingClerkRole.setDescription("Encargado de cobrar las facturas del servicio");

        // Guardar roles
        roleRepository.saveAll(Arrays.asList(adminRole, contractOfficerRole, billingClerkRole));

        // Verificar que los roles se guardaron correctamente
        assertNotNull(adminRole.getRoleId());
        assertNotNull(contractOfficerRole.getRoleId());
        assertNotNull(billingClerkRole.getRoleId());
    }

    @Test
    void testFindAllRoles() {
        // Consultar todos los roles
        List<Role> roles = roleRepository.findAll();

        // Verificar que se obtienen los roles
        assertFalse(roles.isEmpty());
    }
}