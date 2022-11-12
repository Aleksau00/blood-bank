package com.bank.Blood.Bank.controller;

import com.bank.Blood.Bank.dto.CenterDTO;
import com.bank.Blood.Bank.model.Center;
import com.bank.Blood.Bank.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<CenterDTO> getCenter(@PathVariable("id") Integer id){
         Center center = centerService.findOne(id);
        if (center == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(new CenterDTO(center), HttpStatus.OK);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CenterDTO> updateCenter(@RequestBody Center center, @PathVariable("id") Integer id){
        Center editCenter = centerService.update(center, id);
        if (editCenter == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(new CenterDTO(center), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/allNameAsc")
    public ResponseEntity<List<CenterDTO>> getAllByNameAsc(){
        List<Center> centerList = centerService.findAllByOrderByNameAsc();

        List<CenterDTO> centerDTOS = new ArrayList<>();
        for (Center center : centerList){
            centerDTOS.add(new CenterDTO(center));
        }
        return new ResponseEntity<>(centerDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/allNameDesc")
    public ResponseEntity<List<CenterDTO>> getAllByNameDesc(){
        List<Center> centerList = centerService.findAllByOrderByNameDesc();

        List<CenterDTO> centerDTOS = new ArrayList<>();
        for (Center center : centerList){
            centerDTOS.add(new CenterDTO(center));
        }
        return new ResponseEntity<>(centerDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/allCityAsc")
    public ResponseEntity<List<CenterDTO>> getAllByAddressCityAsc(){
        List<Center> centerList = centerService.findAllByOrderByAddressCityAsc();

        List<CenterDTO> centerDTOS = new ArrayList<>();
        for (Center center : centerList){
            centerDTOS.add(new CenterDTO(center));
        }
        return new ResponseEntity<>(centerDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/allCityDesc")
    public ResponseEntity<List<CenterDTO>> getAllByAddressCityDesc(){
        List<Center> centerList = centerService.findAllByOrderByAddressCityDesc();

        List<CenterDTO> centerDTOS = new ArrayList<>();
        for (Center center : centerList){
            centerDTOS.add(new CenterDTO(center));
        }
        return new ResponseEntity<>(centerDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/allCountryAsc")
    public ResponseEntity<List<CenterDTO>> getAllByAddressCountryAsc(){
        List<Center> centerList = centerService.findAllByOrderByAddressCountryAsc();

        List<CenterDTO> centerDTOS = new ArrayList<>();
        for (Center center : centerList){
            centerDTOS.add(new CenterDTO(center));
        }
        return new ResponseEntity<>(centerDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/allCountryDesc")
    public ResponseEntity<List<CenterDTO>> getAllByAddressCountryDesc(){
        List<Center> centerList = centerService.findAllByOrderByAddressCountryDesc();

        List<CenterDTO> centerDTOS = new ArrayList<>();
        for (Center center : centerList){
            centerDTOS.add(new CenterDTO(center));
        }
        return new ResponseEntity<>(centerDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/allAverageGradeAsc")
    public ResponseEntity<List<CenterDTO>> getAllByAverageGradeAsc(){
        List<Center> centerList = centerService.findAllByOrderByAverageGradeAsc();

        List<CenterDTO> centerDTOS = new ArrayList<>();
        for (Center center : centerList){
            centerDTOS.add(new CenterDTO(center));
        }
        return new ResponseEntity<>(centerDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/allAverageGradeDesc")
    public ResponseEntity<List<CenterDTO>> getAllByAverageGradeDesc(){
        List<Center> centerList = centerService.findAllByOrderByAverageGradeDesc();

        List<CenterDTO> centerDTOS = new ArrayList<>();
        for (Center center : centerList){
            centerDTOS.add(new CenterDTO(center));
        }
        return new ResponseEntity<>(centerDTOS, HttpStatus.OK);
    }
}

