package com.bank.Blood.Bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentNoStaffDTO {
    private Integer id;

    LocalDate date;

    LocalTime time;
    private Integer duration;
}
