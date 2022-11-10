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
    public Optional<Center> findOne(Integer id) {
        return centerRepository.findById(id);
    }

    @Override
    public Center save(Center center) {
        return centerRepository.save(center);
    }
}
