package com.example.demo.controllers;

import com.example.demo.models.Contest;
import com.example.demo.models.Person;
import com.example.demo.models.Team;
import com.example.demo.repository.ContestRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TeamRepository;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class MyController {
    @Autowired
    ContestRepository contestRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TeamService teamService;


    /* @RequestMapping(value = "/populate", method = RequestMethod.GET)
    public ResponseEntity populate(){
        superRepository.populate();
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public ResponseEntity<Person> getAllPersons(){
        return new ResponseEntity(personRepository.findAll(),HttpStatus.OK);
    }
    @RequestMapping(value = "/contest", method = RequestMethod.GET)
    public ResponseEntity<Contest> getAllContests(){
        return new ResponseEntity(contestRepository.findAll(),HttpStatus.OK);
    }
    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public ResponseEntity<Team> getAllTeams(){
        return new ResponseEntity(teamRepository.findAll(),HttpStatus.OK);
    }

    @RequestMapping(value = "/contest/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getTeamsByContest(@PathVariable String name){
                Set<Team> queryTeam = new HashSet<>();
                List<Contest> contestsAll=contestRepository.findByName(name);
                for(Contest c: contestsAll){
                    for (Team t: c.getTeams()) {
                        queryTeam.add(t);
                    }
        }
        return new ResponseEntity<Set<Team>>(queryTeam, HttpStatus.OK);
        }

    @RequestMapping(value = "/age", method = RequestMethod.GET)
    public ResponseEntity<?> getTeamsByAge(){
        List<Integer> queryAge = new ArrayList<>();
        List<Team> teamsAll=teamRepository.findAll();
        for(Team t: teamsAll){
            for (Person m: t.getMembers()) {
               // queryAge.add(m.getCalculateAge(m.getBirthDate(), LocalDate.now()));
            }
        }
        Map<Integer, Long> counted = queryAge.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return new ResponseEntity<Map<Integer,Long>>(counted, HttpStatus.OK);
    }

    @RequestMapping(value = "/occupacy/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getContestCapacity(@PathVariable String name){
        List<Contest> queryContest = contestRepository.findByName(name);
        Integer currentSize=0;
        Integer totalSize=0;
        for(Contest c: queryContest){
            totalSize=c.getCapacity();
            for (Team t: c.getTeams()) {
                currentSize+=t.getMembers().size();
            }
        }
        Integer cSize = totalSize-currentSize;
        return new ResponseEntity<Integer>(cSize, HttpStatus.OK);
    }*/

   /* @RequestMapping(value = "/team", method = RequestMethod.POST)
    public ResponseEntity<?> saveTeam(@RequestBody Team team){
        teamService.saveTam(team);
        return new ResponseEntity("hjghj", HttpStatus.OK);

    }*/
}
