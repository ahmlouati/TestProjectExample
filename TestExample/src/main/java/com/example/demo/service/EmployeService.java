package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.EmployeDB;
import com.example.demo.repository.EmployeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeService {

    private final EmployeRepository employeRepository;

    @Transactional(readOnly = true)
    public List<EmployeDB> getAll() {
        return employeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public EmployeDB getOne(Long id) {
        return employeRepository.findById(id).orElseThrow();
    }

    @Transactional
    public EmployeDB save(EmployeDB employeDB) {
        return employeRepository.save(employeDB);
    }

    @Transactional
    public EmployeDB update(EmployeDB employe) {
        EmployeDB employeDB = getOne(employe.getId());
        employeDB.setFullName(employe.getFullName());
        employeDB.setDateOfBirth(employe.getDateOfBirth());
        return employeDB;
    }

    public long count() {
        return employeRepository.count();
    }

}
