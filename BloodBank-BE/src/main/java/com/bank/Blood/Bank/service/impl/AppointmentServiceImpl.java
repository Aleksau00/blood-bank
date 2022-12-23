package com.bank.Blood.Bank.service.impl;

import com.bank.Blood.Bank.dto.AppointmentReportDTO;
import com.bank.Blood.Bank.enums.AppointmentStatus;
import com.bank.Blood.Bank.model.Appointment;
import com.bank.Blood.Bank.repository.AppointmentRepository;
import com.bank.Blood.Bank.service.AppointmentService;
import com.bank.Blood.Bank.service.CenterService;
import com.bank.Blood.Bank.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    @Autowired
    private CenterService centerService;

    @Autowired
    private PollService pollService;
    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository){this.appointmentRepository = appointmentRepository;}
    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> findAllByUserId(int id){
        List<Appointment> allAppointments = appointmentRepository.findAll();
        List<Appointment> userAppointments = new ArrayList<>();
        for (Appointment a : allAppointments) {
            if(a.getRegisteredUser().getId() == id && a.getStatus() == null){
                userAppointments.add(a);
            }
        }
        return userAppointments;
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
    public boolean isURegisteredUserAbleToDonateBlood(int id){
        return pollService.isUserAbleToDonateBlood(id);
    }
}
