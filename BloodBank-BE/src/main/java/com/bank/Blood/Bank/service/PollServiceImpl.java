package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.Poll;
import com.bank.Blood.Bank.model.RegisteredUser;
import com.bank.Blood.Bank.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepository;


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
        return pollRepository.save(poll);
    }
}
