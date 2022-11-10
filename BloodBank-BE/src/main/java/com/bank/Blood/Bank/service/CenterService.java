package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.Center;

import java.util.List;
import java.util.Optional;

public interface CenterService {
   List<Center> findAll();
   Optional<Center> findOne(Integer id);
   Center save(Center center);
}
