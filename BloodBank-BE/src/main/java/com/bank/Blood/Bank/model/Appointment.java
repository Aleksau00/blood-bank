package com.bank.Blood.Bank.model;

import com.bank.Blood.Bank.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registered_user_id", nullable = true)
    private RegisteredUser registeredUser;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "appointment_staff", joinColumns = @JoinColumn(name = "staff_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"))
    private Set<Staff> staffList = new HashSet<Staff>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "center_id", nullable = true)
    private Center center;



    @Column
    private LocalDate date;

    private Integer duration;

    private String description;

    private AppointmentStatus status;




    private LocalTime time;



    public Appointment(Integer id, LocalDate date, LocalTime time, Integer duration, RegisteredUser registeredUser, Center center) {
    }
}
