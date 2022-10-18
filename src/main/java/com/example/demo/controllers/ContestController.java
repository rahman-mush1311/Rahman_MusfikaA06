package com.example.demo.controllers;

import com.example.demo.models.Contest;
import com.example.demo.models.Person;
import com.example.demo.models.Team;
import com.example.demo.repository.ContestRepository;
import com.example.demo.service.ContestService;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
public class ContestController {


    @Autowired
    ContestService contestService;
    @Autowired
    TeamService teamService;
    @Autowired
    ContestRepository contestRepository;
    @RequestMapping(value = "/populatecontest", method = RequestMethod.POST)
    public ResponseEntity<?> createContest(){
       contestService.populateContest();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/contest", method = RequestMethod.GET)
    public ResponseEntity<Team> getAllTeams(){
        return new ResponseEntity(contestRepository.findAll(),HttpStatus.OK);
    }



    @RequestMapping(path="/registrationtocontest", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerContestTeams(@RequestParam(name="contestId") Long contestId,
                                                    @RequestBody Team team) {

        Team success =contestService.contestRegistrationofTeams(contestId, team);
        if(success.getId()!=0)
            return new ResponseEntity<>(success,HttpStatus.OK);

        return new ResponseEntity<>(success,HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(path="/promoteTeams", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> registerContestTeams(@RequestParam(name="contestId") Long contestId,
                                                  @RequestParam(name="teamId") Long teamId) {

        Team promotedTeam =contestService.promotingTeams(contestId, teamId);
        if(promotedTeam.getId()!=0)
            return new ResponseEntity<>(promotedTeam,HttpStatus.OK);

        return new ResponseEntity<>(promotedTeam,HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(path="/editContest", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> editContests(@RequestBody Contest contest) {
        Optional<Contest> queryContest = contestRepository.findById(contest.getId());
        if(queryContest.isPresent()){
        Contest editedContest = contestService.contestEdit(contest);
            return new ResponseEntity<>(editedContest,HttpStatus.OK);
        }
        return new ResponseEntity<>("contest doesn't exsist",HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(path="/setEditable", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> editableContests(@RequestParam(name="contestId") Long contestId) {
        Optional<Contest> queryContest = contestRepository.findById(contestId);
        if(queryContest.isPresent()) {
            Contest modifiedContest = contestService.setEditable(contestId);
            return new ResponseEntity<>(modifiedContest, HttpStatus.OK);
        }
        else return new ResponseEntity<>("contest doesn't exsist",HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path="/setReadble", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> readableContests(@RequestParam(name="contestId") Long contestId) {
        Optional<Contest> queryContest = contestRepository.findById(contestId);
        if(queryContest.isPresent()) {
            Contest modifiedContest = contestService.setReadble(contestId);
            return new ResponseEntity<>(modifiedContest, HttpStatus.OK);
        }
        else return new ResponseEntity<>("contest doesn't exsist",HttpStatus.BAD_REQUEST);
    }

}
