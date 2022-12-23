package com.bank.Blood.Bank.service;
import com.bank.Blood.Bank.dto.AppointmentViewDTO;
import com.bank.Blood.Bank.model.Staff;
import org.springframework.stereotype.Service;
import com.bank.Blood.Bank.model.Appointment;
import java.util.List;
@Service
public interface AppointmentService {
    List<Appointment> findAll();
    Appointment save(Appointment appointment, Integer id);

    List<Appointment> findAllByCenter(Staff staff);

    List<Appointment> findAllByCenterId(Integer id);

    //Appointment getCenterAppointment(Integer id);

    List<Appointment> getAllUserAppointments(Integer id);

    List<AppointmentViewDTO> findAllByCenterDate(Integer id);

    List<AppointmentViewDTO> findAllByCenterTime(Integer id);

    Appointment savePredefined(AppointmentViewDTO appointmentViewDTO, Integer id);

    Boolean cancelAppointment(Integer id);
}
