package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.dto.*;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/appointments")
public class AppointmentController {
    private AppointmentService appointmentService;
    private CenterService centerService;
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

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<List<AppointmentDTO>> getAllByUser(@PathVariable("id") int id){
        List<Appointment> appointmentList = appointmentService.findAllByUserId(id);
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for(Appointment a : appointmentList){
            appointmentDTOS.add(new AppointmentDTO(a));
        }
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('STAFF')")
    @PostMapping("/report")
    public ResponseEntity<Void> report(@RequestBody AppointmentReportDTO appointmentReportDTO){
        appointmentService.report(appointmentReportDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/poll-result/{id}")
    public ResponseEntity<Boolean> isURegisteredUserAbleToDonateBlood(@PathVariable("id") int id) {
        return new ResponseEntity<>(appointmentService.isURegisteredUserAbleToDonateBlood(id), HttpStatus.OK);
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

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(consumes = "application/json", value = "userAppointment/{id}")
    public ResponseEntity<Appointment> saveUserAppointment(@RequestBody Appointment appointment, @PathVariable("id") Integer id) {
        Appointment app = new Appointment();
        app.setDate(appointment.getDate().plusDays(1));
        app.setTime(appointment.getTime());
        app.setDuration(30);
        app.setRegisteredUser(appointment.getRegisteredUser());
        app.setCenter(appointment.getCenter());
        app = appointmentService.save(app, id);
        if (app == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(new Appointment(app.getId(),app.getDate(), app.getTime(), app.getDuration(), app.getRegisteredUser(), app.getCenter()), HttpStatus.CREATED);
    }
/*
    @PreAuthorize("hasAnyAuthority('USER', 'STAFF', 'ADMIN')")
    @GetMapping(value = "/centers/{id}")
    public ResponseEntity<AppointmentDTO> getCenterAppointment(Integer id){
        Appointment appointment = appointmentService.getCenterAppointment(id);
        if (appointment == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(new AppointmentDTO(appointment), HttpStatus.OK);
        }
    }
*/
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(consumes = "application/json", value = "/byUserFuture/{id}")
    public ResponseEntity<List<AppointmentDTO>> getAllFutureUserAppointments(@PathVariable("id") Integer id){
        List<Appointment> appointments = appointmentService.getAllFutureUserAppointments(id);
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for (Appointment appointment : appointments){
            appointmentDTOS.add(new AppointmentDTO(appointment));
        }
    return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(consumes = "application/json", value = "/byUserPast/{id}")
    public ResponseEntity<List<AppointmentDTO>> getAllPastUserAppointments(@PathVariable("id") Integer id){
        List<Appointment> appointments = appointmentService.getAllPastUserAppointments(id);
        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for (Appointment appointment : appointments){
            appointmentDTOS.add(new AppointmentDTO(appointment));
        }
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
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

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(consumes = "application/json", value = "/wanted/{id}")
    public ResponseEntity<AppointmentNoStaffDTO> getWantedAppointmentInCenter(@RequestBody AppoDTO appointment, @PathVariable("id") Integer centerId){
        Appointment wantedAppointment = appointmentService.getWantedAppointmentInCenter(appointment, centerId);
        AppointmentNoStaffDTO a = new AppointmentNoStaffDTO(wantedAppointment.getId(), wantedAppointment.getDate(), wantedAppointment.getTime(), wantedAppointment.getDuration());
        if (wantedAppointment == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {

            return new ResponseEntity<>(a, HttpStatus.OK);
        }
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'STAFF')")
    @PutMapping(consumes = "application/json", value = "/cancel/{id}")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable("id") Integer id) {
        Boolean appointment = appointmentService.cancelAppointment(id);
        if (appointment== false){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/allDurationAsc")
    public ResponseEntity<List<AppointmentDTO>> getAllByDurationAsc(){
        List<Appointment> appointments = appointmentService.findAllByOrderByDurationAsc();

        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for (Appointment appointment : appointments){
            appointmentDTOS.add(new AppointmentDTO(appointment.getId(),appointment.getDate(),appointment.getTime(),appointment.getDuration()));
        }
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/allDurationDesc")
    public ResponseEntity<List<AppointmentDTO>> getAllByDurationDesc(){
        List<Appointment> appointments = appointmentService.findAllByOrderByDurationDesc();

        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        for (Appointment appointment : appointments){
            appointmentDTOS.add(new AppointmentDTO(appointment.getId(),appointment.getDate(),appointment.getTime(),appointment.getDuration()));
        }
        return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
    }


}
