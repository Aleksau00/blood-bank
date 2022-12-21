package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.model.Appointment;
import com.bank.Blood.Bank.model.RegisteredUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter

public class AppointmentDTO {
    private Integer id;
    private RegisteredUserDTO registeredUserDTO;
    LocalDateTime dateTime;
    private Integer duration;

    public AppointmentDTO(Appointment appointment){
        this(appointment.getId(), new RegisteredUserDTO(appointment.getRegisteredUser()), appointment.getDate(), appointment.getDuration());
    }

    public AppointmentDTO(Integer id, RegisteredUserDTO registeredUserDTO, LocalDateTime localDateTime, Integer duration){
        this.id = id;
        this.registeredUserDTO = registeredUserDTO;
        this.dateTime = localDateTime;
        this.duration = duration;
    }
}
