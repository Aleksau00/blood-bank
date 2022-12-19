package com.bank.Blood.Bank.repository;

import com.bank.Blood.Bank.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
