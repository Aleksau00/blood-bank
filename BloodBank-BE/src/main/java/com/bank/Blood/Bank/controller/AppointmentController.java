package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.dto.AppointmentDTO;
import com.bank.Blood.Bank.dto.CenterDTO;
import com.bank.Blood.Bank.dto.RegisteredUserDTO;
import com.bank.Blood.Bank.model.Appointment;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.service.AppointmentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping(value = "api/appointments")
public class AppointmentController {
    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<AppointmentDTO>>getAllAppointments(){
        List<Appointment> appointments = appointmentService.findAll();
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for(Appointment a : appointments) {
            AppointmentDTO appointmentDTO = new AppointmentDTO();
            appointmentDTO.setId(a.getId());
            appointmentDTO.setDate(a.getDate());
            appointmentDTO.setTime(a.getTime());
            appointmentDTO.setDuration(a.getDuration());

            RegisteredUserDTO registeredUserDTO = new RegisteredUserDTO();
            if(a.getRegisteredUser() != null) {
                registeredUserDTO = new RegisteredUserDTO(a.getRegisteredUser());
            }
            appointmentDTO.setRegisteredUserDTO(registeredUserDTO);
            appointmentDTOS.add(appointmentDTO);
        }
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<AppointmentDTO> saveCenterAppointment(@RequestBody AppointmentDTO appointmentDTO) {

        Appointment appointment = new Appointment();
        appointment.setDate(appointmentDTO.getDate());
        appointment.setTime(appointmentDTO.getTime());
        appointment.setDuration(appointmentDTO.getDuration());
        appointment = appointmentService.save(appointment);
        return new ResponseEntity<>(new AppointmentDTO(appointment.getId(),appointment.getDate(), appointmentDTO.getTime(), appointment.getDuration()), HttpStatus.CREATED);
    }
}
