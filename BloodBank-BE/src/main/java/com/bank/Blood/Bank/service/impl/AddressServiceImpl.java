package com.bank.Blood.Bank.service.impl;

import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.repository.AddressRepository;
import com.bank.Blood.Bank.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address update(Address address, Integer id) {
        Optional<Address> editAddress = addressRepository.findById(id);
        return editAddress.isEmpty() ? null : addressRepository.save(address);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> findOne(Integer id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

}
