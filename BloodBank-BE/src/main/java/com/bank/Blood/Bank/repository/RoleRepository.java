package com.bank.Blood.Bank.repository;

import java.util.List;

import com.bank.Blood.Bank.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
}
