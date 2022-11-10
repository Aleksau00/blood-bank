package com.bank.Blood.Bank.service;
import java.util.List;

import com.bank.Blood.Bank.model.AppUser;
import com.bank.Blood.Bank.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public AppUser findOne(Integer id) {
        return appUserRepository.findAppUserById(id);
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public Page<AppUser> findAll(Pageable page) {
        return appUserRepository.findAll(page);
    }

    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public void remove(Integer id) {
        appUserRepository.deleteById(id);
    }


}
