package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.dto.AddressDTO;
import com.bank.Blood.Bank.dto.CenterDTO;
import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.service.AddressService;
import com.bank.Blood.Bank.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Controller
@Transactional
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody Address address, @PathVariable("id") Integer id){
        Address editAddress = addressService.update(address, id);
        if (editAddress == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(new AddressDTO(address), HttpStatus.OK);
        }
    }



}
