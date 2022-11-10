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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blood {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany( cascade = {CascadeType.ALL})
    @JoinTable(name = "center_blood", joinColumns = @JoinColumn(name = "center_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "blood_id", referencedColumnName = "id"))
    private Set<Blood> bloodList = new HashSet<Blood>();

    @Column
    private BloodType type;

    @Column
    private double amount;

}
