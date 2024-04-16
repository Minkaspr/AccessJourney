package com.mk.accessjourneybe.service;


import com.mk.accessjourneybe.entity.User;

import java.util.List;

public interface UserService {

    User getUser(Long id);
    List <User> getAllUsers();
    User createUser(User newUser);
    User editUser(Long id, User updatedUser);
    void deleteUser(Long id);

}
