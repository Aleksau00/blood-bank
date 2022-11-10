package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.model.AppUser;

public class AppUserDTO {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;

    private String email;


    public AppUserDTO() {

    }

    public AppUserDTO(AppUser appUser) {
        this(appUser.getId(), appUser.getUsername(), appUser.getFirstName(), appUser.getLastName(), appUser.getEmail() );
    }

    public AppUserDTO(Integer id, String username, String firstName, String lastName, String email) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}