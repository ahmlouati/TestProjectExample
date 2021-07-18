package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.EmployeDB;

@Repository
public interface EmployeRepository extends JpaRepository<EmployeDB, Long> {

}