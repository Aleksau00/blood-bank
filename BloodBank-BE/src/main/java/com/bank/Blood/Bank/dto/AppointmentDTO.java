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
public class AppointmentDTO {
    private Integer id;
    private RegisteredUserDTO registeredUserDTO;

    LocalDate date;

    LocalTime time;
    private Integer duration;

    public AppointmentDTO(Appointment appointment){
        this(appointment.getId(), new RegisteredUserDTO(appointment.getRegisteredUser()), appointment.getDate(), appointment.getTime(), appointment.getDuration());
    }

    public AppointmentDTO(Integer id, LocalDate localDate, LocalTime localTime, Integer duration){
        this.id = id;
        this.date = localDate;
        this.time = localTime;
        this.duration = duration;
    }

    public AppointmentDTO(Integer id, RegisteredUserDTO registeredUserDTO, LocalDate localDate, LocalTime localTime, Integer duration){
        this.id = id;
        this.registeredUserDTO = registeredUserDTO;
        this.date = localDate;
        this.time = localTime;
        this.duration = duration;
    }
}
