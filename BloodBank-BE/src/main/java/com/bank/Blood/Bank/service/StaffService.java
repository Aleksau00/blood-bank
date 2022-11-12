package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.model.Staff;

public interface StaffService {

    Staff findOne(Integer id);
    Staff update(Staff staff, Integer id);
}
