package com.example.rolepermission_security.repository;

import com.example.rolepermission_security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUserName(String name);
}
