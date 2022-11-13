package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.model.LoyaltyCard;
import com.bank.Blood.Bank.model.RegisteredUser;
import com.bank.Blood.Bank.repository.AddressRepository;
import com.bank.Blood.Bank.repository.CenterRepository;
import com.bank.Blood.Bank.repository.LoyaltyCardRepository;
import com.bank.Blood.Bank.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserService {


    private LoyaltyCardRepository loyaltyCardRepository;
    private AddressRepository addressRepository;
    private RegisteredUserRepository registeredUserRepository;


    @Autowired
    public RegisteredUserServiceImpl(LoyaltyCardRepository loyaltyCardRepository, RegisteredUserRepository registeredUserRepository, AddressRepository addressRepository) {
        this.loyaltyCardRepository = loyaltyCardRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.addressRepository = addressRepository;
    }


    @Override
    public List<RegisteredUser> findAll() {
        return registeredUserRepository.findAll();
    }

    @Override
    public RegisteredUser findOne(int id) {
        Optional<RegisteredUser> user = registeredUserRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public RegisteredUser save(RegisteredUser registeredUser) {
        registeredUser.setPoints(0);
        registeredUser.setIsAuthenticated(false);
        Optional<LoyaltyCard> loyaltyCard = loyaltyCardRepository.findById(1);
        registeredUser.setLoyaltyCard(loyaltyCard.get());
        addressRepository.save(registeredUser.getAddress());
        return registeredUserRepository.save(registeredUser);
    }

    @Override
    public RegisteredUser update(RegisteredUser registeredUser, Integer id){
        Optional<RegisteredUser> optExistingUser = registeredUserRepository.findById(id);
        RegisteredUser existingUser = optExistingUser.get();
        existingUser.setFirstName(registeredUser.getFirstName());
        existingUser.setLastName(registeredUser.getLastName());
        existingUser.setGender(registeredUser.getGender());
        existingUser.setUmcn(registeredUser.getUmcn());
        existingUser.setPhoneNumber(registeredUser.getPhoneNumber());
        existingUser.setInstitution(registeredUser.getInstitution());
        RegisteredUser editedUser = registeredUserRepository.save(existingUser);
        return editedUser;
    }
}
