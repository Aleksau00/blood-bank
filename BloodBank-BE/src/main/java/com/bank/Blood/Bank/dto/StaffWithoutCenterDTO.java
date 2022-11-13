package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.enums.Gender;
import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.model.Appointment;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.model.Staff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class StaffWithoutCenterDTO {
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

    public StaffWithoutCenterDTO(Staff appUser){
        this(appUser.getId(), appUser.getAddress(), appUser.getUsername(), appUser.getFirstName(),
                appUser.getLastName(), appUser.getEmail(), appUser.getPhoneNumber(), appUser.getUmcn(), appUser.getGender(), appUser.getInstitution());}


    public StaffWithoutCenterDTO(Integer id,Address address, String username, String firstName, String lastName, String email, String phoneNumber, String umcn, Gender gender, String institution){
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

}