package com.bank.Blood.Bank.model;

import com.bank.Blood.Bank.enums.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("registeredUser")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RegisteredUser extends AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Boolean isAuthenticated;

    @Column
    private Integer points;

    @Column
    private Integer weight;

    @Column
    private Boolean isCapable; //moze da da krv

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registered_user_id", nullable = true)
    private LoyaltyCard loyaltyCard;

    @Column
    private BloodType bloodType;

    @OneToMany(mappedBy = "registeredUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Feedback> feedbackList = new HashSet<>();

    @OneToMany(mappedBy = "registeredUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Appointment> appointmentList = new HashSet<>();

    public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
        feedback.setRegisteredUser(this);
    }

    public void removeFeedback(Feedback feedback) {
        feedbackList.remove(feedback);
        feedback.setRegisteredUser(null);
    }

    public void addAppointment(Appointment appointment) {
        appointmentList.add(appointment);
        appointment.setRegisteredUser(this);
    }

    public void removeAppointment(Appointment appointment) {
        appointmentList.remove(appointment);
        appointment.setRegisteredUser(null);
    }

}
