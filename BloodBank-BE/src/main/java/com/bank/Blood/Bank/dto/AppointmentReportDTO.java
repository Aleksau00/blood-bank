package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.enums.AppointmentStatus;
import com.bank.Blood.Bank.model.Blood;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentReportDTO {
    private int id;
    private AppointmentStatus status;
    private Integer appointmentEquipment;
    private String description;
    private Blood blood;

}
