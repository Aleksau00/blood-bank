package com.bank.Blood.Bank.service;
import com.bank.Blood.Bank.dto.AppoDTO;
import com.bank.Blood.Bank.dto.AppointmentReportDTO;
import com.bank.Blood.Bank.dto.AppointmentDTO;
import com.bank.Blood.Bank.dto.AppointmentViewDTO;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.model.Staff;
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
    Appointment save(Appointment appointment, Integer id);

    List<Appointment> findAllByCenter(Staff staff);

    List<Appointment> findAllByCenterId(Integer id);

    //Appointment getCenterAppointment(Integer id);

    List<Appointment> getAllFutureUserAppointments(Integer id);

    List<Appointment> getAllPastUserAppointments (Integer id);

    List<AppointmentViewDTO> findAllByCenterDate(Integer id);

    List<AppointmentViewDTO> findAllByCenterTime(Integer id);

    Appointment savePredefined(AppointmentViewDTO appointmentViewDTO, Integer id);
    Appointment getWantedAppointmentInCenter(AppoDTO appointment, Integer centerId);

    Boolean cancelAppointment(Integer id);

    List<Appointment> findAllByOrderByDurationAsc();
    List<Appointment> findAllByOrderByDurationDesc();

    List<Appointment> findAllByOrderByDateDesc();
    List<Appointment> findAllByOrderByDateAsc();
}
