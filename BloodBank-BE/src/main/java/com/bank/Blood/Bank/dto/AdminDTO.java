package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.enums.Gender;
import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.model.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class AdminDTO {
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String umcn;
    private Gender gender;
    private String institution;
    private boolean isFirstLogin;

    public AdminDTO(Admin admin) {
        this(admin.getId(), admin.getAddress(), admin.getUsername(), admin.getPassword(), admin.getFirstName(),
                admin.getLastName(), admin.getPhoneNumber(), admin.getUmcn(), admin.getGender(), admin.getInstitution(), admin.isFirstLogin());
    }


    public AdminDTO(Integer id, Address address, String username, String password, String firstName, String lastName, String phoneNumber, String umcn, Gender gender, String institution, boolean isFirstLogin) {
        this.id = id;
        this.address = address;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.umcn = umcn;
        this.gender = gender;
        this.institution = institution;
        this.isFirstLogin = isFirstLogin;
    }
}