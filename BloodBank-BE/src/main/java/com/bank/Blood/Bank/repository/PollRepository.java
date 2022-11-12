package com.bank.Blood.Bank.repository;

import com.bank.Blood.Bank.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Integer> {
}
