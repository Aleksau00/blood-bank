package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

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
