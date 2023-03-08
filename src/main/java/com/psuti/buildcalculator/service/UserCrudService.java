package com.psuti.buildcalculator.service;

import com.psuti.buildcalculator.entities.User;

import java.util.List;

public interface UserCrudService {
    User create(User user);
    User update(User user, Integer id);
    void removeById(Integer id);
    void remove(User user);
    void removeByUsername(String username);
    List<User> getAll();
    User getById(Integer id);
    User getByUsername(String username);
    boolean existsById(Integer id);
    boolean existsByUsername(String username);
}

