package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.appuser.AppUserService;
import com.bank.Blood.Bank.appuser.EmailValidator;
import com.bank.Blood.Bank.dto.RegisteredUserDTO;
import com.bank.Blood.Bank.model.RegisteredUser;
import com.bank.Blood.Bank.appuser.RegisteredUserService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/registeredUsers")

public class RegisteredUserController {

    private final RegisteredUserService registeredUserService;
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    @Autowired
    public RegisteredUserController(AppUserService appUserService,RegisteredUserService registeredUserService, EmailValidator emailValidator) {
        this.registeredUserService = registeredUserService;
        this.appUserService = appUserService;
        this.emailValidator = emailValidator;
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
        boolean isValid = emailValidator.test(registeredUser.getEmail());
        if(!isValid){
            throw new IllegalStateException("Email is not valid");
        }
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<RegisteredUserDTO> updateUser(@RequestBody RegisteredUser registeredUser, @PathVariable("id") Integer id){
        RegisteredUser editUser = registeredUserService.update(registeredUser, id);
        if (editUser == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(new RegisteredUserDTO(registeredUser), HttpStatus.OK);
        }
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registeredUserService.confirmToken(token);
    }

    /*
    @PutMapping(value = "/{id}")
    public ResponseEntity<PasswordDTO> updatePassword(@RequestBody RegisteredUser registeredUser, @PathVariable("id") Integer id){
        RegisteredUser editUser = registeredUserService.updatePassword(registeredUser, id);
        if (editUser == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(new PasswordDTO(registeredUser), HttpStatus.OK);
        }
    }*/
}
