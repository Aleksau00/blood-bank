package com.bank.Blood.Bank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.bank.Blood.Bank.dto.AppUserDTO;
import com.bank.Blood.Bank.model.AppUser;
import com.bank.Blood.Bank.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
@Transactional
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/users")
public class AppUserController {
    private AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }

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