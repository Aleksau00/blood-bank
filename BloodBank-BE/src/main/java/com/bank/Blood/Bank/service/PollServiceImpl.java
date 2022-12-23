package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.Poll;
import com.bank.Blood.Bank.model.RegisteredUser;
import com.bank.Blood.Bank.repository.PollRepository;
import com.bank.Blood.Bank.appuser.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollServiceImpl implements PollService {

    private RegisteredUserRepository registeredUserRepository;
    private PollRepository pollRepository;

    @Autowired
    public PollServiceImpl(RegisteredUserRepository registeredUserRepository, PollRepository pollRepository){
        this.registeredUserRepository = registeredUserRepository;
        this.pollRepository = pollRepository;
    }


    @Override
    public List<Poll> findAll() {
        return pollRepository.findAll();
    }

    @Override
    public Poll findOne(int id) {
        Optional<Poll> poll = pollRepository.findById(id);
        if (poll.isPresent()) {
            return poll.get();
        }
        return null;
    }

    @Override
    public Poll save(Poll poll) {;
         Poll savedPoll = pollRepository.save(poll);
         Optional<RegisteredUser> ru = registeredUserRepository.findById(poll.getRegisteredUser().getId());
         ru.get().setPoll(poll);
         registeredUserRepository.save(ru.get());
         return savedPoll;
    }
    @Override
    public boolean isUserAbleToDonateBlood(int id) {
        for (Poll p : pollRepository.findAll()) {
            if (p.getRegisteredUser().getId() == id ){
                if (p.getQuestion1() || p.getQuestion2() || p.getQuestion3() || p.getQuestion4() || p.getQuestion5()
                        || p.getQuestion6() || p.getQuestion7()){
                    return false;
                }
            }

        }
        return true;
    }
}
