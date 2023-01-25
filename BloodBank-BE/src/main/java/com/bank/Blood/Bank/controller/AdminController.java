package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.dto.AdminDTO;
import com.bank.Blood.Bank.dto.EditStaffDTO;
import com.bank.Blood.Bank.model.Admin;
import com.bank.Blood.Bank.model.Staff;
import com.bank.Blood.Bank.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/admins")

public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){ this.adminService = adminService;}

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable("id") Integer id){
        Admin admin = adminService.findOne(id);
        if (admin == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(new AdminDTO(admin), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<AdminDTO> updateAdmin(@RequestBody Admin admin, @PathVariable("id") Integer id){
        Admin editAdmin = adminService.update(admin, id);
        if (editAdmin == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(new AdminDTO(admin), HttpStatus.OK);
        }
    }

}
