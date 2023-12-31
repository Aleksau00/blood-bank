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

    private StaffRepository staffRepository;
    private CenterRepository centerRepository;
    private AddressRepository addressRepository;
    @Autowired
    private AddressService addressService = new AddressServiceImpl();
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
        staff.setIsEnabled(true);
        staff.setIsLocked(false);
        addressService.update(staff.getAddress(), staff.getAddress().getId());
        staffRepository.save(staff);
        return staff;
    }

    @Override
    public Staff save(Staff staff) {
        staff.setIsLocked(false);
        staff.setIsEnabled(true);
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
