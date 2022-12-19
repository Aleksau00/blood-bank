package com.bank.Blood.Bank.appuser;
import java.util.List;

import com.bank.Blood.Bank.appuser.AppUser;
import org.springframework.stereotype.Service;

@Service
public interface AppUserService {

    /*
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
*/

    List<AppUser> findAll();

}
