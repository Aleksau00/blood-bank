package com.bank.Blood.Bank.controller;

import java.util.ArrayList;
import java.util.List;

import com.bank.Blood.Bank.dto.AppUserDTO;
import com.bank.Blood.Bank.appuser.AppUser;
import com.bank.Blood.Bank.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.transaction.Transactional;

@Controller
@Transactional
@CrossOrigin(origins = "http://localhost:4200")

@RestController

@RequestMapping(value = "api/users")
public class AppUserController {
    private AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }
    @PreAuthorize("hasAnyAuthority('STAFF', 'ADMIN')")
    @GetMapping(value = "/all")
    public ResponseEntity<List<AppUserDTO>> getAllUsers() {
        List<AppUser> users = appUserService.findAll();

        // convert students to DTOs
        List<AppUserDTO> usersDTO = new ArrayList<>();
        for (AppUser a : users) {
            usersDTO.add(new AppUserDTO(a));
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

//    // GET /api/students?page=0&size=5&sort=firstName,DESC
//    @GetMapping
//    public ResponseEntity<List<StudentDTO>> getStudentsPage(Pageable page) {
//
//        // page object holds data about pagination and sorting
//        // the object is created based on the url parameters "page", "size" and "sort"
//        Page<Student> students = studentService.findAll(page);
//
//        // convert students to DTOs
//        List<StudentDTO> studentsDTO = new ArrayList<>();
//        for (Student s : students) {
//            studentsDTO.add(new StudentDTO(s));
//        }
//
//        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
//    }

}