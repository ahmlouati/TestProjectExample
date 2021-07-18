package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entities.EmployeDB;

@DataJpaTest
@ActiveProfiles("test")
class EmployeRepositoryTest {

    @Autowired
    private EmployeRepository dao;

    @Test
    void listEmployes_ShouldReturnAllEmployes() {
        long count = dao.count();
        assertEquals(5, count);
    }

    @Test
    public void getEmployeWithValidId_ShouldReturnEmploye() {
        Optional<EmployeDB> findById = dao.findById(1L);
        assertTrue(findById.isPresent());
    }

    @Test
    public void getEmployeWithInvalidId_ShouldReturnEmptyOptional() {
        Optional<EmployeDB> findById = dao.findById(99L);
        assertFalse(findById.isPresent());
    }

    @Test
    public void validEmploye_ShouldBeCreated() {
        EmployeDB employeDB = new EmployeDB();
        employeDB.setId(6L);
        employeDB.setFullName("Amine");
        LocalDate localDate = LocalDate.parse("1991-03-15");
        employeDB.setDateOfBirth(localDate);
        dao.save(employeDB);

        List<EmployeDB> employeDBs = dao.findAll();        
        assertEquals(6, employeDBs.size());
        assertEquals("Amine", employeDBs.get(5).getFullName());
        assertTrue(localDate.isEqual(employeDBs.get(5).getDateOfBirth()));
    }

    @Test
    public void deleteEmploye_ShouldRemoveEmploye() {
        dao.deleteById(1L);
        long count = dao.count();
        assertEquals(4, count);
    }

}
