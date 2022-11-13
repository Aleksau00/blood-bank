package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.model.Staff;

import java.util.List;

public interface StaffService {

    Staff findOne(Integer id);
    Staff update(Staff staff, Integer id);

    Staff save(Staff staff);

    List<Staff> findAll();
}
