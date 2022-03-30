package com.example.rolepermission_security.repository;

import com.example.rolepermission_security.entity.Role;
import com.example.rolepermission_security.entity.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {


    Optional<Role>findByName(RoleEnum name);
}
