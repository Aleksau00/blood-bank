package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.dto.AppointmentDTO;
import com.bank.Blood.Bank.model.Appointment;
import com.bank.Blood.Bank.service.AppointmentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        for(Appointment a : appointments){
            appointmentDTOS.add(new AppointmentDTO(a));
        }
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }
}
