package com.example.demo.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.EmployeDB;
import com.example.demo.model.JwtResponse;
import com.example.demo.service.EmployeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/employe")
@AllArgsConstructor
public class EmployeController {
    
    private final EmployeService employeService;

	@GetMapping
	public ResponseEntity<List<EmployeDB>> getAll(@AuthenticationPrincipal final JwtResponse defmarketUser) {
	    List<EmployeDB> all = employeService.getAll();
	    return ResponseEntity.ok(all);
	}
	
	@PutMapping
	public ResponseEntity<EmployeDB> update(@RequestBody EmployeDB employeDB){
	    EmployeDB updated = employeService.update(employeDB);
	    return ResponseEntity.ok().body(updated);
	}
	
	@PostMapping
    public ResponseEntity<EmployeDB> save(@RequestBody EmployeDB employeDB){
        EmployeDB employe = employeService.save(employeDB);
        return ResponseEntity.ok().body(employe);
    }

}