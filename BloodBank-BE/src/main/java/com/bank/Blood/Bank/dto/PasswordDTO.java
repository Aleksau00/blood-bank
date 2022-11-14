package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.model.RegisteredUser;

import java.util.Optional;

public class PasswordDTO {

    private Integer id;

    private String password;

    public PasswordDTO() {

    }

    public PasswordDTO(RegisteredUser appUser) {
        this(appUser.getId(), appUser.getPassword());
    }

    public PasswordDTO(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    public PasswordDTO(Optional<RegisteredUser> registeredUser) {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
