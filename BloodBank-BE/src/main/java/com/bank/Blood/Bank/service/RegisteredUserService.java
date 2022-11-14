package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.RegisteredUser;

import java.util.List;
import java.util.Optional;

public interface RegisteredUserService {

    List<RegisteredUser> findAll();

    RegisteredUser findOne(int id);

    RegisteredUser save(RegisteredUser registeredUser);

    RegisteredUser update(RegisteredUser registeredUser, Integer id);

    RegisteredUser updatePassword(RegisteredUser registeredUser, Integer id);
}
