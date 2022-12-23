package com.bank.Blood.Bank.service.impl;

import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.model.Blood;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.repository.AddressRepository;
import com.bank.Blood.Bank.repository.CenterRepository;
import com.bank.Blood.Bank.service.AddressService;
import com.bank.Blood.Bank.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public void changeBloodAndEquipment(Blood blood, Integer usedEquipment, Integer centerId){
        Optional<Center> optionalCenter = centerRepository.findById(centerId);
        if(optionalCenter.isEmpty()){
            return;
        }
        Center center = optionalCenter.get();
        changeEquipment(usedEquipment, center);
        changeBlood(blood, center);
        centerRepository.save(center);
    }

    @Override
    public void changeEquipment(Integer usedEquipment, Center center){
        Integer newEquipment = center.getEquipment() - usedEquipment;
        if(newEquipment < 0){
            newEquipment = 0;
        }
        center.setEquipment(newEquipment);
    }
    @Override
    public void changeBlood(Blood blood, Center center){
        List<Blood> bloodList = center.getTypesOfBlood();
        if(bloodList == null){
            bloodList = new ArrayList<>();
        }
        if (!BloodTypeExist(blood, bloodList)){
            bloodList.add(blood);
        }
        for (Blood b: bloodList){
            if(blood.getType() == b.getType()){
                int index = bloodList.indexOf(b);
                b.setAmount(b.getAmount() + blood.getAmount());
                bloodList.set(index, b);
            }
        }
        center.setTypesOfBlood(bloodList);
    }

    @Override
    public boolean BloodTypeExist(Blood blood, List<Blood> bloodList){
        for(Blood b : bloodList){
            if (blood.getType() == b.getType() && blood.getType().isPositive() == b.getType().isPositive()){
                return true;
            }
        }
        return false;
    }


}
