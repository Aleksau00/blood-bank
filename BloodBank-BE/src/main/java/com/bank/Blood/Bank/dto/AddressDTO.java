package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.model.Center;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDTO {

    private Integer id;
    private String country;
    private String city;
    private String street;
    private String number;
    private String postalCode;

    public AddressDTO(Address address){
        this(address.getId(), address.getCountry(), address.getCity(), address.getStreet(), address.getNumber(), address.getPostalCode());
    }

}
