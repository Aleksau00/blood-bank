package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.model.Staff;
import com.bank.Blood.Bank.service.CenterService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class CenterDTO {

    private Integer id;
    private String name;
    private Address address;
    private String description;
    private double averageGrade;
    private LocalTime startTime;
    private LocalTime endTime;

    public CenterDTO(Center center){
        this(center.getId(), center.getName(), center.getAddress(), center.getDescription(), center.getAverageGrade(), center.getStartTime(), center.getEndTime());
    }

    public CenterDTO(Integer id, String name, Address address, String description, double averageGrade, LocalTime startTime, LocalTime endTime){
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.averageGrade = averageGrade;
        this.startTime = startTime;
        this.endTime = endTime;
    }


}
