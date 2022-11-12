package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.Poll;

import java.util.List;

public interface PollService {
    List<Poll> findAll();
    Poll findOne(int id);
    Poll save(Poll poll);
}
