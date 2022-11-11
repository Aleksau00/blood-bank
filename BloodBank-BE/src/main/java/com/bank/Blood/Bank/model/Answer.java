package com.bank.Blood.Bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "feedback_id", nullable = true)
    private Feedback feedback;
}
