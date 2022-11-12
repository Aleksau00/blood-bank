package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.enums.Gender;
import com.bank.Blood.Bank.model.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class AppUserDTO {
    private Integer id;
    private String username;
    private String password;
    private String email;

    private String firstName;
    private String lastName;

    private String phoneNumber;
    private String umcn;
    private Gender gender;
    private String institution;

    public AppUserDTO(AppUser appUser) {
        this(appUser.getId(), appUser.getUsername(), appUser.getPassword(), appUser.getEmail(), appUser.getFirstName(), appUser.getLastName(),  appUser.getPhoneNumber(), appUser.getUmcn(), appUser.getGender(), appUser.getInstitution());
    }

    public AppUserDTO(Integer id, String username, String password, String email, String firstName, String lastName, String phoneNumber, String umcn, Gender gender, String institution) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.umcn = umcn;
        this.gender = gender;
        this.institution = institution;
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