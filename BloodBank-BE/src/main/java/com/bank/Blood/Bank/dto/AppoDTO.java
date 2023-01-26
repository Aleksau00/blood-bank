package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.model.RegisteredUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AppoDTO {

    private LocalDate date;

    private LocalTime time;


}
