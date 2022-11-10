package com.bank.Blood.Bank.dto;

import com.bank.Blood.Bank.model.Address;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.service.CenterService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.print.DocFlavor;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
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



}
