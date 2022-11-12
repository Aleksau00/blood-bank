package com.bank.Blood.Bank.repository;

import com.bank.Blood.Bank.model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RegisteredUserRepository  extends JpaRepository<RegisteredUser, Integer> {
}
