package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.dto.CenterDTO;
import com.bank.Blood.Bank.dto.EditStaffDTO;
import com.bank.Blood.Bank.dto.StaffDTO;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.model.Staff;
import com.bank.Blood.Bank.service.AddressService;
import com.bank.Blood.Bank.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
@Transactional
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/staff")
public class StaffController {

    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService){ this.staffService = staffService;}

    @GetMapping(value = "/{id}")
    public ResponseEntity<EditStaffDTO> getStaff(@PathVariable("id") Integer id){
        Staff staff = staffService.findOne(id);
        if (staff == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(new EditStaffDTO(staff), HttpStatus.OK);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EditStaffDTO> updateStaff(@RequestBody Staff staff, @PathVariable("id") Integer id){
        Staff editStaff = staffService.update(staff, id);
        if (editStaff == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(new EditStaffDTO(staff), HttpStatus.OK);
        }
    }
}
