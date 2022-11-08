package com.bank.Blood.Bank.repository;

import com.bank.Blood.Bank.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    public AppUser findAppUserById(Integer id);


    public Page<AppUser> findAll(Pageable pageable);



}