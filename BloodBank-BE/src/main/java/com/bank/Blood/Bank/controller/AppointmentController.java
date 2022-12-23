package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.dto.AppointmentDTO;
import com.bank.Blood.Bank.dto.AppointmentReportDTO;
import com.bank.Blood.Bank.model.Appointment;
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
        for(Appointment a : appointments){
            appointmentDTOS.add(new AppointmentDTO(a));
        }
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<List<AppointmentDTO>> getAllByUser(@PathVariable("id") int id){
        List<Appointment> appointmentList = appointmentService.findAllByUserId(id);
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for(Appointment a : appointmentList){
            appointmentDTOS.add(new AppointmentDTO(a));
        }
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }

    @PostMapping("/report")
    public ResponseEntity<Void> report(@RequestBody AppointmentReportDTO appointmentReportDTO){
        appointmentService.report(appointmentReportDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/poll-result/{id}")
    public ResponseEntity<Boolean> isURegisteredUserAbleToDonateBlood(@PathVariable("id") int id){
        return new ResponseEntity<>(appointmentService.isURegisteredUserAbleToDonateBlood(id), HttpStatus.OK);
    }
}
