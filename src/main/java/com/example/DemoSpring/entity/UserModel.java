package com.example.DemoSpring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Full name is required")
    private String fullname;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    private String message;

    private LocalDate birthday;
    private LocalDateTime updatedAt;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}
