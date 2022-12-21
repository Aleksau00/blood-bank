package com.bank.Blood.Bank.model;

import com.bank.Blood.Bank.appuser.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("staff")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Staff extends AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany( cascade = {CascadeType.ALL})
    @JoinTable(name = "appointment_staff", joinColumns = @JoinColumn(name = "staff_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "id"))
    private Set<Appointment> appointments = new HashSet<Appointment>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "center_id", nullable = true)
    private Center center;


}
