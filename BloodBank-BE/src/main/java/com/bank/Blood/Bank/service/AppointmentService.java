package com.bank.Blood.Bank.service;
import com.bank.Blood.Bank.dto.AppointmentReportDTO;
import org.springframework.stereotype.Service;
import com.bank.Blood.Bank.model.Appointment;

import java.util.List;
@Service
public interface AppointmentService {
    List<Appointment> findAll();

    List<Appointment> findAllByUserId(int id);

    void report(AppointmentReportDTO appointmentReportDTO);

    void addAppointmentReport(AppointmentReportDTO appointmentReportDTO, Appointment appointment);

    boolean isURegisteredUserAbleToDonateBlood(int id);
}
