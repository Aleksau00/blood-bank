package com.bank.Blood.Bank.repository;

import com.bank.Blood.Bank.model.Center;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CenterRepository extends JpaRepository<Center, Integer>  {

    public List<Center> findAllByOrderByNameAsc();

    public List<Center> findAllByOrderByNameDesc();

    public List<Center> findAllByOrderByAddressCityAsc();

    public List<Center> findAllByOrderByAddressCityDesc();
    public List<Center> findAllByOrderByAddressCountryAsc();
    public List<Center> findAllByOrderByAddressCountryDesc();
    public List<Center> findAllByOrderByAverageGradeAsc();

    public List<Center> findAllByOrderByAverageGradeDesc();


}
