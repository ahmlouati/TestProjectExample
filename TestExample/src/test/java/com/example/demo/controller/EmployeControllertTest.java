package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.config.JwtTokenUtil;
import com.example.demo.entities.EmployeDB;
import com.example.demo.model.JwtRequest;
import com.example.demo.service.EmployeService;
import com.example.demo.service.JwtUserDetailsService;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeControllertTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeService service;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private JwtUserDetailsService userDetailsService;

    List<EmployeDB> allEmployees;

    @BeforeEach
    public void setup() {
        EmployeDB john = new EmployeDB();
        john.setFullName("John");
        LocalDate localDate = LocalDate.parse("1980-12-20");
        john.setDateOfBirth(localDate);

        allEmployees = Arrays.asList(john);
        when(service.getAll()).thenReturn(allEmployees);
    }

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnUnauthorized() throws Exception {
        mvc.perform(get("/api/employe").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
    }
    
    @Test
    public void shouldGenerateAuthToken() throws Exception {
        JwtRequest authenticationRequest = new JwtRequest("javainuse","password");        
        UserDetails user = new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                new ArrayList<>()); 
        when(userDetailsService.loadUserByUsername(authenticationRequest.getUsername())).thenReturn(user);
        String token = jwtTokenUtil.generateToken(user);;
        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get("/api/employe").header("Authorization", "Bearer " + token))
        .andExpect(status().isOk());
    }

}
