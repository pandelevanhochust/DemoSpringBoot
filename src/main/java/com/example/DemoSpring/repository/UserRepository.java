package com.example.DemoSpring.repository;

import com.example.DemoSpring.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByUsername(String username);
}
