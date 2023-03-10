package com.psuti.buildcalculator.dao;

import com.psuti.buildcalculator.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    void removeByEmail(String email);
}

