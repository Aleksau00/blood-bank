package com.bank.Blood.Bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;

    @Column
    private String description;

    @Column
    private double averageGrade;


    @Column
    private LocalTime startTime;

    @Column
    private LocalTime endTime;

    @ManyToMany( cascade = {CascadeType.ALL})
    @JoinTable(name = "center_blood", joinColumns = @JoinColumn(name = "center_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "blood_id", referencedColumnName = "id"))
    private Set<Center> bloodSet = new HashSet<Center>();

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Equipment> equipmentList = new HashSet<>();

    @OneToMany(mappedBy = "center", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Staff> staffList = new HashSet<>();

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Appointment> appointmentList = new HashSet<>();

    public void addStaff(Staff staff) {
        staffList.add(staff);
        staff.setCenter(this);
    }

    public void removeStaff(Staff staff) {
        staffList.remove(staff);
        staff.setCenter(null);
    }

    public void addTerm(Appointment appointment) {
        appointmentList.add(appointment);
        appointment.setCenter(this);
    }

    public void removeTerm(Appointment appointment) {
        appointmentList.remove(appointment);
        appointment.setCenter(null);
    }

    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
        equipment.setCenter(this);
    }

    public void removeEquipment(Equipment equipment) {
        equipmentList.remove(equipment);
        equipment.setCenter(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Center a = (Center) o;
        return id != null && id.equals(a.getId());
    }

    @Override
    public int hashCode() {

        return 1337;
    }
    @Column(columnDefinition = "integer default 15")
    private Integer equipment;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Blood> typesOfBlood = new ArrayList<>();
}


