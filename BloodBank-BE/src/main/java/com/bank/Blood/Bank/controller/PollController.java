package com.bank.Blood.Bank.controller;


import com.bank.Blood.Bank.dto.PollDTO;
import com.bank.Blood.Bank.dto.RegisteredUserDTO;
import com.bank.Blood.Bank.model.Poll;
import com.bank.Blood.Bank.model.RegisteredUser;
import com.bank.Blood.Bank.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/polls")
public class PollController {

    private final PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'STAFF', 'USER')")
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PollDTO> getUser(@PathVariable("id") Integer id) {
        Poll poll = pollService.findOne(id);
        if (poll == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PollDTO pollDTO = new PollDTO(poll);
        return new ResponseEntity<>(pollDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll)  {
        Poll savedPoll = null;
        try {
            savedPoll = pollService.save(poll);
            if (savedPoll == null) {
                System.out.println("Y");
                return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
            }
            System.out.println("Y");
            return new ResponseEntity<Poll>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Poll>(savedPoll, HttpStatus.CONFLICT);
        }
    }


}
