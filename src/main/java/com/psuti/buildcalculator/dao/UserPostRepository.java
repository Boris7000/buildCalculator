package com.psuti.buildcalculator.dao;

import com.psuti.buildcalculator.entities.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostRepository extends JpaRepository<UserPost, Integer> {
}
