package com.bank.Blood.Bank.service.impl;

import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.repository.AddressRepository;
import com.bank.Blood.Bank.repository.CenterRepository;
import com.bank.Blood.Bank.service.AddressService;
import com.bank.Blood.Bank.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CenterServiceImpl implements CenterService {


    private CenterRepository centerRepository;
    private AddressRepository addressRepository;

    @Autowired
    public CenterServiceImpl(CenterRepository centerRepository, AddressRepository addressRepository){
        this.centerRepository = centerRepository;
        this.addressRepository = addressRepository;
    }

    @Autowired
    private AddressService addressService = new AddressServiceImpl();

    @Override
    public List<Center> findAll() {
        return centerRepository.findAll();
    }

    @Override
    public List<Center> findAllByOrderByNameAsc() {
        return centerRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Center> findAllByOrderByNameDesc() {
        return centerRepository.findAllByOrderByNameDesc();
    }

    @Override
    public List<Center> findAllByOrderByAddressCityAsc() {
        return centerRepository.findAllByOrderByAddressCityAsc();
    }

    @Override
    public List<Center> findAllByOrderByAddressCityDesc() {
        return centerRepository.findAllByOrderByAddressCityDesc();
    }

    @Override
    public List<Center> findAllByOrderByAddressCountryAsc() {
        return centerRepository.findAllByOrderByAddressCountryAsc();
    }

    @Override
    public List<Center> findAllByOrderByAddressCountryDesc() {
        return centerRepository.findAllByOrderByAddressCountryDesc();
    }

    @Override
    public List<Center> findAllByOrderByAverageGradeDesc() {
        return centerRepository.findAllByOrderByAverageGradeDesc();
    }

    @Override
    public List<Center> findAllByOrderByAverageGradeAsc() {
        return centerRepository.findAllByOrderByAverageGradeAsc();
    }

    @Override
    public Center findOne(Integer id) {
       Optional<Center> center = centerRepository.findById(id);
       return center.isEmpty() ? null : center.get();
    }

    @Override
    public Center update(Center center, Integer id){
        Optional<Center> editCenter = centerRepository.findById(id);
        addressService.update(center.getAddress(), center.getAddress().getId());
        Center editedCenter = centerRepository.save(center);
        return editCenter.isEmpty() ? null : editedCenter;
    }



    @Override
    public Center save(Center center) {
        addressRepository.save(center.getAddress());
        return centerRepository.save(center);
    }
}
