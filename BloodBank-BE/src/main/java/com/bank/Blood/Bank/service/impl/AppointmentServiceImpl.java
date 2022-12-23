package com.bank.Blood.Bank.service.impl;

import com.bank.Blood.Bank.appuser.RegisteredUserRepository;
import com.bank.Blood.Bank.dto.AppointmentDTO;
import com.bank.Blood.Bank.dto.AppointmentViewDTO;
import com.bank.Blood.Bank.dto.RegisteredUserDTO;
import com.bank.Blood.Bank.email.EmailSender;
import com.bank.Blood.Bank.model.Appointment;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.model.RegisteredUser;
import com.bank.Blood.Bank.model.Staff;
import com.bank.Blood.Bank.repository.AppointmentRepository;
import com.bank.Blood.Bank.repository.CenterRepository;
import com.bank.Blood.Bank.repository.StaffRepository;
import com.bank.Blood.Bank.service.AppointmentService;
import com.bank.Blood.Bank.service.CenterService;
import com.bank.Blood.Bank.service.StaffService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private CenterService centerService;
    private final CenterRepository centerRepository;

    private final StaffRepository staffRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final EmailSender emailSender;

    @Autowired
    public AppointmentServiceImpl(EmailSender emailSender, AppointmentRepository appointmentRepository,
                                  CenterRepository centerRepository, StaffRepository staffRepository,
                                  RegisteredUserRepository registeredUserRepository) {
        this.appointmentRepository = appointmentRepository;
        this.centerRepository = centerRepository;
        this.staffRepository = staffRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.emailSender = emailSender;
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment save(Appointment appointment, Integer id) {
        Optional<Staff> staff = staffRepository.findById(id);
        Center center = staff.get().getCenter();
        appointment.setCenter(center);
        if (!validateWorkingHours(appointment, center)) {
            throw new IllegalStateException("Working hours!");
        }
        if (!validateCenterAvailability(appointment, center, staff.get())) {
            throw new IllegalStateException("Center availability!");
        }
        if (!validateBeforeToday(appointment)) {
            throw new IllegalStateException("Can't add an appointment in the past!");
        }
        return appointmentRepository.save(appointment);
    }

    public boolean validateWorkingHours(Appointment appointment, Center center) {
        LocalTime startTime = appointment.getTime();
        Duration duration = Duration.ofMinutes(appointment.getDuration());
        LocalTime endTime = startTime.plus(duration);
        LocalTime centerStartTime = center.getStartTime();
        LocalTime centerEndTime = center.getEndTime();

        //checks if appointment starts and ends in center working hours
        if (!(startTime.isAfter(centerStartTime) && endTime.isBefore(centerEndTime))) {
            return false;
        }
        return true;
    }

    public boolean validateBeforeToday(Appointment appointment) {
        LocalDate startDate = appointment.getDate();
        LocalDate now = LocalDate.now();
        //checks if appointment starts and ends in center working hours
        if (startDate.isBefore(now)) {
            return false;
        }
        return true;
    }

    public boolean validateCenterAvailability(Appointment appointment, Center center, Staff staff) {
        List<Appointment> existingAppointments = findAllByCenter(staff);
        LocalTime startTime = appointment.getTime();
        Duration appDuration = Duration.ofMinutes(appointment.getDuration());
        LocalTime endTime = startTime.plus(appDuration);

        for (Appointment existingAppointment : existingAppointments) {

            LocalTime existingStartTime = existingAppointment.getTime();
            Duration duration = Duration.ofMinutes(existingAppointment.getDuration());
            LocalTime existingEndTime = existingStartTime.plus(duration);

            //checks if new appointment falls within the time of the existing appointment
            if (startTime.isAfter(existingStartTime) && startTime.isBefore(existingEndTime)) {
                return false;
            }

            //checks if the end time of the new appointment falls within the time of the existing appointment.
            if (endTime.isAfter(existingStartTime) && endTime.isBefore(existingEndTime)) {
                return false;
            }

            //checks if the start time of the existing appointment falls within the time of the new appointment
            if (existingStartTime.isAfter(startTime) && existingStartTime.isBefore(endTime)) {
                return false;
            }

            //checks if the end time of the existing appointment falls within the time of the new appointment
            if (existingEndTime.isAfter(startTime) && existingEndTime.isBefore(endTime)) {
                return false;
            }
        }
        return true;
    }

    public List<Appointment> findAllByCenter(Staff staff) {

        List<Appointment> allAppointments = findAll();
        List<Appointment> centerAppointments = new ArrayList<Appointment>();
        for (Appointment ap : allAppointments) {
            if (ap.getCenter().getId().equals(staff.getCenter().getId())) {
                centerAppointments.add(ap);
            }
        }
        return centerAppointments;
    }

    @Override
    public List<AppointmentViewDTO> findAllByCenterDate(Integer id) {
        List<Appointment> appointmentsSorted = appointmentRepository.findAllByOrderByDateAsc();
        List<AppointmentViewDTO> appointments = new ArrayList<AppointmentViewDTO>();
        for (Appointment a: appointmentsSorted) {
            if (a.getCenter().getId().equals(id) && a.getRegisteredUser() == null) {
                AppointmentViewDTO avd = new AppointmentViewDTO();
                avd.setId(a.getId());
                avd.setDate(a.getDate());
                avd.setTime(a.getTime());
                avd.setDuration(a.getDuration());
                appointments.add(avd);
            }
        }
        return appointments;
    }

    @Override
    public List<AppointmentViewDTO> findAllByCenterTime(Integer id) {
        List<Appointment> appointmentsSorted = appointmentRepository.findAllByOrderByTimeAsc();
        List<AppointmentViewDTO> appointments = new ArrayList<AppointmentViewDTO>();
        for (Appointment a: appointmentsSorted) {
            if (a.getCenter().getId().equals(id) && a.getRegisteredUser() == null && a.getDate().isAfter(LocalDate.now().plusDays(1))) {
                AppointmentViewDTO avd = new AppointmentViewDTO();
                avd.setId(a.getId());
                avd.setDate(a.getDate());
                avd.setTime(a.getTime());
                avd.setDuration(a.getDuration());
                appointments.add(avd);
            }
        }
        return appointments;
    }

    @Override
    public Appointment savePredefined(AppointmentViewDTO appointmentViewDTO, Integer id) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentViewDTO.getId());
        Optional<RegisteredUser> registeredUser = registeredUserRepository.findById(id);
        appointment.get().setRegisteredUser(registeredUser.get());
        if(registeredUser.get().getPoll().getDate().isBefore(LocalDate.now().minusMonths(5).minusDays(25))  || registeredUser.get().getPoll() == null){
            return null;
        }
        emailSender.send(registeredUser.get().getUsername(), "Your appointment has been scheduled for "+appointment.get().getDate().toString()+" at "+appointment.get().getTime());
        return appointmentRepository.save(appointment.get());
    }


}




