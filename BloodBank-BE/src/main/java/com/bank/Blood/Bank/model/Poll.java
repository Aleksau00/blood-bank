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

    //zdrav sposoban odmoran?
    @Column
    private Boolean question1;

    //promene na kozi/infekcije/gljivicna oboljenja?
    @Column
    private Boolean question2;

    //suvise visok ili nizak pritisak?
    @Column
    private Boolean question3;

    //pod terapijom ili nije proslo 7 dana od terapije?
    @Column
    private Boolean question4;

    //menstrualni ciklus?
    @Column
    private Boolean question5;


    //stomatoloska intervencija u prethodnih 7 dana?
    @Column
    private Boolean question6;

    //pirsing/tetovaza/hirurska intervencija u prethodnih 6 meseci?
    @Column
    private Boolean question7;


}
