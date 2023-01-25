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
public class EditStaffDTO {
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

    public EditStaffDTO(Staff appUser){
        this(appUser.getId(), appUser.getAddress(), appUser.getUsername(), appUser.getPassword(), appUser.getFirstName(),
                appUser.getLastName(), appUser.getPhoneNumber(), appUser.getUmcn(), appUser.getGender(), appUser.getInstitution());}


    public EditStaffDTO(Integer id,Address address, String username, String password, String firstName, String lastName, String phoneNumber, String umcn, Gender gender, String institution){
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
    }

}