package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.model.Appointment;
import com.bank.Blood.Bank.model.RegisteredUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class AppointmentViewDTO {
    private Integer id;

    LocalDate date;

    LocalTime time;
    private Integer duration;

    public AppointmentViewDTO(Appointment appointment){
        this(appointment.getId(), appointment.getDate(), appointment.getTime(), appointment.getDuration());
    }

    public AppointmentViewDTO(Integer id, LocalDate localDate, LocalTime localTime, Integer duration){
        this.id = id;
        this.date = localDate;
        this.time = localTime;
        this.duration = duration;
    }


}
