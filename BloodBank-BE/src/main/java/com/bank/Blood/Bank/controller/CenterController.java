package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.dto.CenterDTO;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@RequestMapping(value = "api/centers")
public class CenterController {

    private CenterService centerService;

    @Autowired
    public CenterController(CenterService centerService){
        this.centerService = centerService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<CenterDTO>> getAllCenters(){
        List<Center> centerList = centerService.findAll();

        List<CenterDTO> centerDTOS = new ArrayList<>();
        for (Center center : centerList){
            centerDTOS.add(new CenterDTO(center));
        }
        return new ResponseEntity<>(centerDTOS, HttpStatus.OK);
    }
}
