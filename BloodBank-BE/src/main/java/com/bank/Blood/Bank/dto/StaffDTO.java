package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.enums.Gender;
import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.model.Staff;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StaffDTO {
    private String username;
    private String firstName;
    private String lastName;
    private Address address;
    private String phoneNumber;
    private String umcn;
    private Gender gender;
    private String institution;
    private CenterDTO centerDTO;

    public StaffDTO(Staff appUser){
        this(appUser.getAddress(), appUser.getUsername(), appUser.getFirstName(),
                appUser.getLastName(), appUser.getPhoneNumber(), appUser.getUmcn(), appUser.getGender(), appUser.getInstitution(), new CenterDTO(appUser.getCenter()));}


    public StaffDTO(Address address, String username, String firstName, String lastName,  String phoneNumber, String umcn, Gender gender, String institution, CenterDTO centerDTO){
        this.address = address;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.umcn = umcn;
        this.gender = gender;
        this.institution = institution;
        this.centerDTO = centerDTO;
    }

}
