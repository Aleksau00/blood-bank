package com.bank.Blood.Bank.service.impl;

import com.bank.Blood.Bank.appuser.RegisteredUserService;
import com.bank.Blood.Bank.dto.*;
import com.bank.Blood.Bank.enums.AppointmentStatus;
import com.bank.Blood.Bank.appuser.RegisteredUserRepository;
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
import com.bank.Blood.Bank.service.PollService;
import com.bank.Blood.Bank.service.StaffService;
import net.bytebuddy.asm.Advice;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private CenterService centerService;

    private PollService pollService;
    private final CenterRepository centerRepository;

    private final StaffRepository staffRepository;
    private final RegisteredUserRepository registeredUserRepository;
    private final EmailSender emailSender;
    private final RegisteredUserService registeredUserService;

    @Autowired
    public AppointmentServiceImpl(EmailSender emailSender, AppointmentRepository appointmentRepository,
                                  CenterRepository centerRepository, StaffRepository staffRepository,
                                  RegisteredUserRepository registeredUserRepository,
                                  RegisteredUserService registeredUserService, PollService pollService) {
        this.appointmentRepository = appointmentRepository;
        this.centerRepository = centerRepository;
        this.staffRepository = staffRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.emailSender = emailSender;
        this.registeredUserService = registeredUserService;
        this.pollService = pollService;
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findAllByUserId(int id) {
        List<Appointment> allAppointments = appointmentRepository.findAll();
        List<Appointment> userAppointments = new ArrayList<>();
        for (Appointment a : allAppointments) {
            if(a.getRegisteredUser() == null) continue;
            if (a.getRegisteredUser().getId() == id && a.getStatus() == null) {
                userAppointments.add(a);
            }
        }
        return userAppointments;
    }
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
        LocalDate startDate = appointment.getDate();
        Duration appDuration = Duration.ofMinutes(appointment.getDuration());
        LocalTime endTime = startTime.plus(appDuration);

        for (Appointment existingAppointment : existingAppointments) {

            LocalTime existingStartTime = existingAppointment.getTime();
            LocalDate existingDate = existingAppointment.getDate();
            Duration duration = Duration.ofMinutes(existingAppointment.getDuration());
            LocalTime existingEndTime = existingStartTime.plus(duration);

            if(existingDate.equals(startDate)) {
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
        }
        return true;
    }

    //By center_id from center administrator
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

    public List<Appointment> findAllByCenterId(Integer id) {
        //return appointmentRepository.findAllByCenterId(id);
        List<Appointment> allAppointments = appointmentRepository.findAll();
        List<Appointment> centerAppointments = new ArrayList<Appointment>();
        for(Appointment ap : allAppointments) {
            if(ap.getCenter().getId().equals(id)) {
                centerAppointments.add(ap);
            }
        }
        return centerAppointments;
    }

    /*public Appointment getCenterAppointment(Integer id) {
        List<Appointment> centerAppointments = findAllByCenterId(id);
        for(Appointment appointment: centerAppointments) {
            //if(appointment.getDate().equals())
        }
    }*/

    public List<Appointment> getAllFutureUserAppointments(Integer id) {
        List<Appointment> allAppointments = appointmentRepository.findAll();
        List<Appointment> userAppointments = new ArrayList<Appointment>();
        for(Appointment ap : allAppointments) {
            if(ap.getRegisteredUser() != null){
                if(ap.getRegisteredUser().getId().equals(id)) {
                    if(ap.getDate().isAfter(LocalDate.now())) {
                        userAppointments.add(ap);
                    }
                }
            }
        }
        return userAppointments;
    }

    public List<Appointment> getAllPastUserAppointments(Integer id) {
        List<Appointment> allAppointments = appointmentRepository.findAll();
        List<Appointment> userAppointments = new ArrayList<Appointment>();
        for(Appointment ap : allAppointments) {
            if(ap.getRegisteredUser() != null){
                if(ap.getRegisteredUser().getId().equals(id)) {
                    if(ap.getDate().isBefore(LocalDate.now())) {
                        userAppointments.add(ap);
                    }
                }
            }
        }
        return userAppointments;
    }

    @Override
    public List<Appointment> findAllByOrderByDurationDesc() {
        return appointmentRepository.findAllByOrderByDurationDesc();
    }

    @Override
    public List<Appointment> findAllByOrderByDurationAsc() {

        return appointmentRepository.findAllByOrderByDurationAsc();
    }


    @Override
    public void report(AppointmentReportDTO appointmentReportDTO){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentReportDTO.getId());
        if(optionalAppointment.isEmpty()){
            return;
        }
        Appointment appointment = optionalAppointment.get();
        addAppointmentReport(appointmentReportDTO, appointment);
        appointmentRepository.save(appointment);
    }

    @Override
    public void addAppointmentReport(AppointmentReportDTO appointmentReportDTO, Appointment appointment) {
        if (appointment.getStatus() == AppointmentStatus.DENIED || appointment.getStatus() == AppointmentStatus.CANCELED
                || appointment.getStatus() == AppointmentStatus.FINISHED) {
            return;
        }
        switch (appointmentReportDTO.getStatus()) {
            case DENIED : {
                appointment.setStatus(AppointmentStatus.DENIED);
                break;
            }
            case CANCELED : {
                appointment.setStatus(AppointmentStatus.CANCELED);
                registeredUserService.addPenalty(appointment.getRegisteredUser());
                break;
            }
            case FINISHED : {
                appointment.setStatus(AppointmentStatus.FINISHED);
                appointment.setDescription(appointmentReportDTO.getDescription());
                centerService.changeBloodAndEquipment(appointmentReportDTO.getBlood(), appointmentReportDTO.getAppointmentEquipment(), appointment.getCenter().getId());
            }
        }
    }

    @Override
    public boolean isURegisteredUserAbleToDonateBlood(int id) {
        return pollService.isUserAbleToDonateBlood(id);
    }
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
        System.out.println(appointmentViewDTO);
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentViewDTO.getId());
        Optional<RegisteredUser> registeredUser = registeredUserRepository.findById(id);
        appointment.get().setRegisteredUser(registeredUser.get());
        if(registeredUser.get().getPoll().getDate().isBefore(LocalDate.now().minusMonths(5).minusDays(25))  || registeredUser.get().getPoll() == null){
            return null;
        }
        emailSender.send(registeredUser.get().getUsername(), "Your appointment has been scheduled for "+appointment.get().getDate().toString()+" at "+appointment.get().getTime());
        return appointmentRepository.save(appointment.get());
    }
    @Override
    public Appointment getWantedAppointmentInCenter(AppoDTO appointment, Integer centerId) {
        List<Appointment> centersAppointmetd = appointmentRepository.findAllByCenterId(centerId);
        LocalDate realDate = appointment.getDate().plusDays(1);
        appointment.setDate(realDate);

            for(Appointment centerAppointment : centersAppointmetd) {
                if(hasAppointment(centerAppointment,appointment)) {
                    return centerAppointment;
                }
            }
        throw new IllegalStateException("Appointment not found in selected center");
    }



    public boolean hasAppointment(Appointment centerAppointment, AppoDTO appointment) {
        LocalTime centerAppointmentStartTime = centerAppointment.getTime();
        Duration centerAppointmentDuration = Duration.ofMinutes(centerAppointment.getDuration());
        LocalTime centerAppointmentEndTime = centerAppointmentStartTime.plus(centerAppointmentDuration);
        LocalDate centerAppointmentDate = centerAppointment.getDate();

        LocalTime newAppointmentStartTime = appointment.getTime();
        LocalTime newAppointmentEndTime = newAppointmentStartTime.plusMinutes(30);
        LocalDate newAppointmentDate = appointment.getDate();

        if(centerAppointmentDate.equals(newAppointmentDate)) {
            /*
            if(newAppointmentStartTime.isAfter(centerAppointmentStartTime) && newAppointmentStartTime.isBefore(centerAppointmentEndTime)) {
                return true;
            }
            if(newAppointmentEndTime.isAfter(centerAppointmentStartTime) && newAppointmentEndTime.isBefore(centerAppointmentEndTime)) {
                return true;
            }
            if(centerAppointmentStartTime.isAfter(newAppointmentStartTime) && centerAppointmentStartTime.isBefore(newAppointmentEndTime)) {
                return true;
            }
            if(centerAppointmentEndTime.isAfter(newAppointmentStartTime) && centerAppointmentEndTime.isBefore(newAppointmentEndTime)) {
                return true;
            }
            */
            if(newAppointmentStartTime.equals(centerAppointmentStartTime)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Boolean cancelAppointment(Integer id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        if(appointment.get().getDate().isEqual(LocalDate.now()) || appointment.get().getDate().isBefore(LocalDate.now()))
        {
            return false;
        }
        appointment.get().setRegisteredUser(null);
        appointmentRepository.save(appointment.get());
        return true;
    }
}




