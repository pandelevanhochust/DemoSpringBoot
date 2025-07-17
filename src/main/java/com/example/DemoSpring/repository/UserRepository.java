package com.example.DemoSpring.repository;

import com.example.DemoSpring.entity.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByUsername(String username);
    // JpaRepo auto generated methods
    Page<UserModel> findByFullnameStartingWith(String prefix, Pageable pageable);
    Page<UserModel> findByFullnameIsLike(String fullname, Pageable pageable);
    Page<UserModel> findByFullnameContaining(String fullname, Pageable pageable);
}
