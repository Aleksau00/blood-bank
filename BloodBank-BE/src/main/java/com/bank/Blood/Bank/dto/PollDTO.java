package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.model.Poll;
import com.bank.Blood.Bank.model.RegisteredUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PollDTO {

    private Integer id;
    private RegisteredUserDTO registeredUserDTO;
    private String occupation;
    private Integer donationCount;
    private LocalDate date;

    public PollDTO(Poll poll){
        this(poll.getId(), new RegisteredUserDTO(poll.getRegisteredUser()), poll.getOccupation(), poll.getDonationCount(), poll.getDate());
    }
}
