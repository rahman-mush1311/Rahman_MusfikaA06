package com.example.demo.controllers;

import com.example.demo.models.Person;
import com.example.demo.models.Team;
import com.example.demo.repository.TeamRepository;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;
    @Autowired
    TeamRepository teamRepository;


    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public ResponseEntity<Team> getAllTeams(){
        return new ResponseEntity(teamRepository.findAll(),HttpStatus.OK);
    }

    @RequestMapping(path="/editTeam", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> editTeams(@RequestParam(name="teamId") Long teamId,
                                                  @RequestBody Team team) {
        boolean edited=false;
        Optional<Team> queryTeam = teamRepository.findById(teamId);
        if(queryTeam.isPresent()) {
            edited = teamService.editTeam(teamId, team);
            if (edited)
                return new ResponseEntity<>("sucessful edit", HttpStatus.OK);
        }
        return new ResponseEntity<>("unsucessful edit",HttpStatus.BAD_REQUEST);

    }

}
