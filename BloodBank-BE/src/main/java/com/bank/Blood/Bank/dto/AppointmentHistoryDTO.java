package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.enums.AppointmentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@NoArgsConstructor
@Getter
@Setter
public class AppointmentHistoryDTO {
    private int id;
    private AppointmentStatus status;
    private Integer appointmentEquipment;
    private String description;
    private Integer duration;

    LocalDate date;

    LocalTime time;
}
