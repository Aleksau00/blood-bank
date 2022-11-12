package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.Center;

import java.util.List;
import java.util.Optional;

public interface CenterService {
   List<Center> findAll();
   Center findOne(Integer id);
   Center update(Center center, Integer id);
   Center save(Center center);
   List<Center> findAllByOrderByNameAsc();
   List<Center> findAllByOrderByNameDesc();
   List<Center> findAllByOrderByAddressCityAsc();
   List<Center> findAllByOrderByAddressCityDesc();
   List<Center> findAllByOrderByAddressCountryAsc();
   List<Center> findAllByOrderByAddressCountryDesc();
   List<Center> findAllByOrderByAverageGradeDesc();
   List<Center> findAllByOrderByAverageGradeAsc();

}
