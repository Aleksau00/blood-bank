package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.RegisteredUser;
import com.bank.Blood.Bank.repository.AddressRepository;
import com.bank.Blood.Bank.repository.CenterRepository;
import com.bank.Blood.Bank.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserService {



    private AddressRepository addressRepository;
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    public RegisteredUserServiceImpl(RegisteredUserRepository registeredUserRepository, AddressRepository addressRepository) {
        this.registeredUserRepository = registeredUserRepository;
        this.addressRepository = addressRepository;
    }


    @Override
    public List<RegisteredUser> findAll() {
        return registeredUserRepository.findAll();
    }

    @Override
    public RegisteredUser findOne(int id) {
        Optional<RegisteredUser> user = registeredUserRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }


    @Override
    public RegisteredUser save(RegisteredUser registeredUser) {
        addressRepository.save(registeredUser.getAddress());
        return registeredUserRepository.save(registeredUser);
    }
}
