package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.RegisteredUser;
import com.bank.Blood.Bank.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserService {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

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
        return registeredUserRepository.save(registeredUser);
    }
}
