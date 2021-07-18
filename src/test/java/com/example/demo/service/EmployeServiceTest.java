package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.entities.EmployeDB;
import com.example.demo.repository.EmployeRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeServiceTest {

    @MockBean
    private EmployeRepository repository;
    
    @Autowired
    private EmployeService service;
    
    Optional<EmployeDB> optional;
    
    EmployeDB employe = new EmployeDB();
    
    @BeforeEach
    public void setup() {        
        employe.setId(1L);
        employe.setFullName("Amine");
        LocalDate localDate = LocalDate.parse("1991-03-15");
        employe.setDateOfBirth(localDate);
        optional = Optional.of(employe);
        when(repository.findById(1L)).thenReturn(optional);
    }
    
    @Test
    void listEmployes_ShouldReturnAllEmployes() {
        when(repository.count()).thenReturn(5L);
        long count = service.count();
        assertEquals(5, count);
    }
    
    @Test
    public void getEmployeWithValidId_ShouldReturnEmploye() {        
        EmployeDB employeDB = service.getOne(1L);
        assertEquals(employeDB, optional.get());        
    }
    
    @Test
    public void updateEmployeWithValidId_ShouldReturnEmploye() {
        EmployeDB employe = new EmployeDB();
        employe.setId(1L);
        employe.setFullName("Amin");
        EmployeDB update = service.update(employe);
        assertEquals(update.getFullName(), employe.getFullName());
    }
}
