package com.psuti.buildcalculator.dao;

import com.psuti.buildcalculator.entities.UserState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStateRepository extends JpaRepository<UserState, Integer> {
}
