package com.example.rolepermission_security.repository;

import com.example.rolepermission_security.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
}
