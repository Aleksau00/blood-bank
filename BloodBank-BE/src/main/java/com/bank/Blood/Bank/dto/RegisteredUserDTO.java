package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.enums.Gender;
import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.model.RegisteredUser;

import java.util.Optional;

public class RegisteredUserDTO {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;

    private String email;

    private Address address;

    private String phoneNumber;
    private String umcn;
    private Gender gender;
    private String institution;


    public RegisteredUserDTO() {

    }

    public RegisteredUserDTO(RegisteredUser appUser) {
        this(appUser.getId(), appUser.getAddress(), appUser.getUsername(), appUser.getFirstName(),
                appUser.getLastName(), appUser.getEmail(), appUser.getPhoneNumber(), appUser.getUmcn(), appUser.getGender(), appUser.getInstitution());
    }

    public RegisteredUserDTO(Integer id, Address address, String username, String firstName, String lastName, String email, String phoneNumber, String umcn, Gender gender, String institution) {
        this.id = id;
        this.address = address;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.umcn = umcn;
        this.gender = gender;
        this.institution = institution;
    }

    public RegisteredUserDTO(Optional<RegisteredUser> registeredUser) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUmcn() {
        return umcn;
    }

    public void setUmcn(String umcn) {
        this.umcn = umcn;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }
}