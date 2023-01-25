package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.Blood;
import com.bank.Blood.Bank.model.Appointment;
import com.bank.Blood.Bank.model.Center;

import javax.persistence.criteria.CriteriaBuilder;
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
   void changeBloodAndEquipment(Blood blood, Integer usedEquipment, Integer centerId);
   void changeEquipment(Integer usedEquipment, Center center);
   void changeBlood(Blood blood, Center center);
   boolean BloodTypeExist(Blood blood, List<Blood> bloodList);
   List<Center> getAllAvailableCenters(Appointment appointment);

}
