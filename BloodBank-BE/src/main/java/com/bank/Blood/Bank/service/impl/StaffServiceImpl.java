package com.bank.Blood.Bank.service.impl;

import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.model.Staff;
import com.bank.Blood.Bank.repository.StaffRepository;
import com.bank.Blood.Bank.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public Staff findOne(Integer id) {
        Optional<Staff> staff = staffRepository.findById(id);
        return staff.isEmpty() ? null : staff.get();
    }

    @Override
    public Staff update(Staff staff, Integer id){
        Optional<Staff> editStaff = staffRepository.findById(id);
        Staff editedStaff = staffRepository.save(staff);
        return editStaff.isEmpty() ? null : editedStaff;
    }
}
