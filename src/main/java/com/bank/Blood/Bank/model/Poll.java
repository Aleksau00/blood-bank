package com.bank.Blood.Bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registered_user_id", nullable = true)
    private RegisteredUser registeredUser;

    @Column
    private String occupation;

    @Column
    private Integer donationCount;

    @Column
    private LocalDate date;
}
