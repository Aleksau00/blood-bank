package com.bank.Blood.Bank.appuser;

import com.bank.Blood.Bank.appuser.AppUser;
import com.bank.Blood.Bank.model.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RegisteredUserRepository  extends JpaRepository<RegisteredUser, Integer> {
    Optional<AppUser> findByEmail(String s);

    @Transactional
    @Modifying
    @Query("UPDATE RegisteredUser a " +
            "SET a.isEnabled = TRUE WHERE a.email = ?1")
    int enableRegisteredUser(String email);

}

