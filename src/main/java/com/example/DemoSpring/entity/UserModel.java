package com.example.DemoSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data // generate setter and getter for all fields
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")

public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullname;
    private String username;
    private String password;
    private String message;
    private LocalDate birthday;
    private LocalDateTime updatedAt;
}
