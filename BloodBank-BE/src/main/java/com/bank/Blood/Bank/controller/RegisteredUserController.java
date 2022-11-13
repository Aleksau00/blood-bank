package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.dto.RegisteredUserDTO;
import com.bank.Blood.Bank.model.RegisteredUser;
import com.bank.Blood.Bank.service.RegisteredUserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/registeredUsers")

public class RegisteredUserController {

    private final RegisteredUserService registeredUserService;

    @Autowired
    public RegisteredUserController(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredUserDTO> getUser(@PathVariable("id") Integer id) {
        RegisteredUser user = registeredUserService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RegisteredUserDTO registeredUserDTO = new RegisteredUserDTO(user);
        return new ResponseEntity<>(registeredUserDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredUser> createUser(@Valid @RequestBody RegisteredUser registeredUser) throws ConstraintViolationException {
        RegisteredUser savedUser = null;
        try {
            savedUser = registeredUserService.save(registeredUser);
            if (savedUser == null) {
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
            return new ResponseEntity<RegisteredUser>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<RegisteredUser>(savedUser, HttpStatus.CONFLICT);
        }
    }
}
