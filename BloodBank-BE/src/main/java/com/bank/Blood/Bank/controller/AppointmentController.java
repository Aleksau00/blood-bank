package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.dto.AppointmentDTO;
import com.bank.Blood.Bank.dto.AppointmentViewDTO;
import com.bank.Blood.Bank.dto.CenterDTO;
import com.bank.Blood.Bank.dto.RegisteredUserDTO;
import com.bank.Blood.Bank.model.Appointment;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.service.AppointmentService;
import com.bank.Blood.Bank.service.CenterService;
import com.bank.Blood.Bank.service.StaffService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/appointments")
public class AppointmentController {
    private AppointmentService appointmentService;
    private StaffService staffService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, StaffService staffService) {
        this.appointmentService = appointmentService;
        this.staffService = staffService;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'STAFF', 'ADMIN')")
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

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'STAFF')")
    @PostMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<AppointmentDTO> saveCenterAppointment(@RequestBody AppointmentDTO appointmentDTO, @PathVariable("id") Integer id) {
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentDTO.getDate().plusDays(1));
        appointment.setTime(appointmentDTO.getTime());
        appointment.setDuration(appointmentDTO.getDuration());
        appointment = appointmentService.save(appointment, id);
        return new ResponseEntity<>(new AppointmentDTO(appointment.getId(),appointment.getDate(), appointmentDTO.getTime(), appointment.getDuration()), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping(value = "/predefined/Date/{id}")
    public ResponseEntity<List<AppointmentViewDTO>> getAllFromCenterByDate(@PathVariable("id") Integer id) {
        List<AppointmentViewDTO> appointments = appointmentService.findAllByCenterDate(id);
        if (appointments == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping(value = "/predefined/Time/{id}")
    public ResponseEntity<List<AppointmentViewDTO>> getAllFromCenterByTime(@PathVariable("id") Integer id) {
        List<AppointmentViewDTO> appointments = appointmentService.findAllByCenterTime(id);
        if (appointments == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'STAFF')")
    @PutMapping(consumes = "application/json", value = "/predefined/{id}")
    public ResponseEntity<Appointment> updateCenterAppointment(@RequestBody AppointmentViewDTO appointmentViewDTO, @PathVariable("id") Integer id) {
        Appointment appointment = appointmentService.savePredefined(appointmentViewDTO, id);
        if (appointment== null){
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
