package com.bank.Blood.Bank.service;


import com.bank.Blood.Bank.model.Admin;

public interface AdminService {
    Admin findOne(Integer id);
    Admin update(Admin admin, Integer id);
}
