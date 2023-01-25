package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.dto.CenterDTO;
import com.bank.Blood.Bank.dto.EditStaffDTO;
import com.bank.Blood.Bank.dto.StaffDTO;
import com.bank.Blood.Bank.dto.StaffWithoutCenterDTO;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.model.RegisteredUser;
import com.bank.Blood.Bank.model.Staff;
import com.bank.Blood.Bank.service.AddressService;
import com.bank.Blood.Bank.service.StaffService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/staff")
public class StaffController {

    private StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService){ this.staffService = staffService;}

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @GetMapping(value = "/all")
    public ResponseEntity<List<StaffDTO>> getAllStaff(){
        List<Staff> staffList = staffService.findAll();

        List<StaffDTO> staffDTOS = new ArrayList<>();
        for (Staff staff : staffList){
            staffDTOS.add(new StaffDTO(staff));
        }
        return new ResponseEntity<>(staffDTOS, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<EditStaffDTO> getStaff(@PathVariable("id") Integer id){
        Staff staff = staffService.findOne(id);
        if (staff == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(new EditStaffDTO(staff), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<EditStaffDTO> updateStaff(@RequestBody Staff staff, @PathVariable("id") Integer id){
        Staff editStaff = staffService.update(staff, id);
        if (editStaff == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(new EditStaffDTO(staff), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Staff> createUser(@RequestBody Staff staff) throws ConstraintViolationException {
        Staff savedStaff = null;
        try {
            savedStaff = staffService.save(staff);
            if (savedStaff == null) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
            return new ResponseEntity<Staff>(savedStaff, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Staff>(savedStaff, HttpStatus.CONFLICT);
        }
    }
}
