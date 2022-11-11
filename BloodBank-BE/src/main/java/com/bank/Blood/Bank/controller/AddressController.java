package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Controller
@Transactional
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService){ this.addressService = addressService;}


}
