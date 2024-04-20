package com.mk.accessjourneybe.service.Impl;

import com.mk.accessjourneybe.entity.User;
import com.mk.accessjourneybe.repository.UserRepository;
import com.mk.accessjourneybe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User editUser(Long id, User updatedUser) {
        // Encuentra el usuario existente
        User existingUser = getUser(id);

        // Actualiza los campos del usuario existente
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setIdentityDocumentType(updatedUser.getIdentityDocumentType());
        existingUser.setIdentityDocumentNumber(updatedUser.getIdentityDocumentNumber());
        existingUser.setDateOfBirth(updatedUser.getDateOfBirth());
        existingUser.setIsActive(updatedUser.getIsActive());
        existingUser.setRole(updatedUser.getRole());

        // Guarda y devuelve el usuario actualizado
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
