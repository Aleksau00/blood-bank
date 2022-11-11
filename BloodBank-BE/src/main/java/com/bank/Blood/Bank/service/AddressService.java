package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.model.Center;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> findAll();
    Optional<Address> findOne(Integer id);
    Address save(Address address);
}
