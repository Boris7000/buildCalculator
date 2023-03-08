package com.psuti.buildcalculator.dao;

import com.psuti.buildcalculator.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByValue(String value);
    Optional<Token> findTokenByUserId(Integer id);
}
