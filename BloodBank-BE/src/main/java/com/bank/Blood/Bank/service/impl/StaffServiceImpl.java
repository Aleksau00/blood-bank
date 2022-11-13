package com.bank.Blood.Bank.service.impl;
import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.model.Staff;
import com.bank.Blood.Bank.repository.AddressRepository;
import com.bank.Blood.Bank.repository.CenterRepository;
import com.bank.Blood.Bank.repository.StaffRepository;
import com.bank.Blood.Bank.service.AddressService;
import com.bank.Blood.Bank.service.CenterService;
import com.bank.Blood.Bank.service.StaffService;
import org.hibernate.loader.ColumnEntityAliases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;
    private CenterRepository centerRepository;
    private AddressRepository addressRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CenterService centerService;
    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository, CenterRepository centerRepository, AddressRepository addressRepository ){
        this.staffRepository = staffRepository;
        this.addressRepository = addressRepository;
        this.centerRepository = centerRepository;
    }

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

    @Override
    public Staff save(Staff staff) {
        addressRepository.save(staff.getCenter().getAddress());
        centerRepository.save(staff.getCenter());
        addressRepository.save(staff.getAddress());
        return staffRepository.save(staff);
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }
}
