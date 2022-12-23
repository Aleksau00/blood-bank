package com.bank.Blood.Bank.model;

import com.bank.Blood.Bank.appuser.AppUser;
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
    private Integer points;

    @Column
    private Integer weight;

    @Column
    private Boolean isCapable; //moze da da krv

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loyalty_card_id", nullable = true)
    private LoyaltyCard loyaltyCard;

    @Column
    private BloodType bloodType;

    @OneToMany(mappedBy = "registeredUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Feedback> feedbackList = new HashSet<>();

    @OneToMany(mappedBy = "registeredUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Appointment> appointmentList = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "poll_id", nullable = true)
    private Poll poll;

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
