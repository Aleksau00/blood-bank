package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.repository.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CenterServiceImpl implements  CenterService{

    @Autowired
    private CenterRepository centerRepository;

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
    public Optional<Center> findOne(Integer id) {
        return centerRepository.findById(id);
    }

    @Override
    public Center save(Center center) {
        return centerRepository.save(center);
    }
}
