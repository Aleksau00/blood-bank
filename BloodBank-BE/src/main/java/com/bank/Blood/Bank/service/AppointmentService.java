package com.bank.Blood.Bank.service;
import com.bank.Blood.Bank.model.Center;
import org.springframework.stereotype.Service;
import com.bank.Blood.Bank.model.Appointment;

import java.util.List;
@Service
public interface AppointmentService {
    List<Appointment> findAll();
    Appointment save(Appointment appointment);
}
