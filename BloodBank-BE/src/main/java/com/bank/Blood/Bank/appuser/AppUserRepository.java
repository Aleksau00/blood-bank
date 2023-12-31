package com.bank.Blood.Bank.appuser;

import com.bank.Blood.Bank.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    public AppUser findAppUserById(Integer id);


    //public Page<AppUser> findAll(Pageable pageable);

    public List<AppUser> findAll();

    AppUser findByUsername(String username);
}