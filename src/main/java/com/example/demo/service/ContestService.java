package com.example.demo.service;

import com.example.demo.models.Contest;
import com.example.demo.models.Person;
import com.example.demo.models.Team;
import com.example.demo.repository.ContestRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TeamRepository;
import org.apache.commons.lang3.function.FailableLongSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import org.apache.commons.lang3.ObjectUtils;

import static com.example.demo.models.Team.State.Pending;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class ContestService {

    @Autowired
    ContestRepository contestRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PersonService personService;
    @Autowired
    PersonRepository personRepository;

    public void populateContest() {
        Contest contest = new Contest();
        contest.setCapacity(60);
        contest.setName("preli00");
        contest.setDate(LocalDate.of(
                2022, 3, 11
        ));
        List<Person> managerFind = personService.getPersonbyId(1L); //change the id
        Set<Person> managers = new HashSet<>(managerFind);
        contest.setManger(managers);
        contest.setRegistration_allowed(TRUE);
        contest.setRegistration_to(LocalDate.of(
                2022, 3, 11
        ));
        contest.setRegistration_from(LocalDate.of(2022, 4, 30));
       // contest.setReadEnable(FALSE);
        contest.setWriteEnable(TRUE);
        contestRepository.save(contest);

        Contest contest1 = new Contest();
        contest1.setCapacity(20);
        contest1.setName("regional");
        contest1.setDate(LocalDate.of(
                2022, 11, 11
        ));
        Set<Person> manager = new HashSet<>();

        List<Person> managerFind01 = personService.getPersonbyId(2L); //change the id
        Set<Person> managers01 = new HashSet<>(managerFind01);
        contest1.setManger(managers01);
        contest1.setRegistration_allowed(FALSE);
        contest1.setRegistration_to(LocalDate.of(
                2022, 9, 10
        ));
        contest1.setRegistration_from(LocalDate.of(2022, 11, 9));

        List<Contest> subContestList = new ArrayList<>();
        subContestList.add(contest);
        contest1.setSubcontests(subContestList);
       // contest1.setReadEnable(FALSE);
        contest1.setWriteEnable(TRUE);
        contestRepository.save(contest1);
    }
    public Team contestRegistrationofTeams(Long contestId, Team teamSent){
        Team tempTeam = teamSent;

        if(checkUniqueness(contestId,tempTeam)){
           if(getAgeValidity(tempTeam)){
               Optional<Contest> contestList = contestRepository.findById(contestId);
               Contest contestQuery = contestList.get();
               if(getContestCapacity(contestId)) {
                   if (contestQuery.isWriteEnable()) {
                       tempTeam.setState(Pending);
                       tempTeam.setRank(0);
                       teamRepository.save(tempTeam);
                       List<Team> contestTeams = contestQuery.getTeams();
                       contestTeams.add(tempTeam);
                       contestQuery.setTeams(contestTeams);
                       contestRepository.save(contestQuery);
                      // return TRUE;
                       return  tempTeam;
                   }
                   else return tempTeam;
               }
               else
                   return tempTeam;
           }
           else return tempTeam;
        }
       // return FALSE;
        return  tempTeam;

    }
    public boolean getContestCapacity(Long contestId) {
        Optional<Contest> queryContest = contestRepository.findById(contestId);
        Integer totalSize = 0;
        Integer currentSize = 0;
        Contest contestExsisting = new Contest();
        if (queryContest.isPresent()) {
            totalSize = queryContest.get().getCapacity();
            for (Team t : queryContest.get().getTeams()) {
                currentSize += t.getMembers().size();
            }
        }
        if (totalSize - currentSize >= 4) {
            //System.out.println(totalSize-currentSize);
            return TRUE;
        }
        //System.out.println(totalSize-currentSize);
        return FALSE;
    }
    public boolean checkUniqueness(Long contestId, Team teamSent){
        List<Person> teamPersons = teamSent.getMembers();
        boolean isHere = false;
        if(teamSent.getMembers().size()!=3 || teamSent.getMembers().isEmpty() ){
            return FALSE;
        }
        for(int i=0; i<teamSent.getMembers().size(); i++){
            if(teamSent.getMembers().get(i).getId()==0){
                Person p1 = new Person();
                p1=teamSent.getMembers().get(i);
                List<Person> personExsists = personRepository.findByEmail(p1.getEmail());
                if(!personExsists.isEmpty()){
                    isHere=true;
                    System.out.println("person hash working");
                    teamPersons.remove(teamSent.getMembers().get(i));
                    teamPersons.add(i,personExsists.get(0));
                    personExsists.clear();
                }

                if(!isHere)
                  personRepository.save(p1);
                isHere=false;
            }
            else{
                Optional<Contest> contestList = contestRepository.findById(contestId);
                if(contestList.isPresent()){
                    List<Team> teamsList = contestList.get().getTeams();
                    for(int t=0; t<teamsList.size();t++){
                        Team currentTeam = teamsList.get(t);
                        List<Person> currentTeamPersons = currentTeam.getMembers();
                        for(int k=0; k<currentTeamPersons.size(); k++) {
                            Person currentPersons = currentTeamPersons.get(k);
                            Optional<Person> fetchPerson = personRepository.findById(teamSent.getMembers().get(i).getId());
                            Person checkingPerson = fetchPerson.get();
                            boolean isExsisting = currentPersons.hashCode() == checkingPerson.hashCode();
                            if (isExsisting) {
                               // System.out.println("Hash coding working");
                                return FALSE;
                            }
                           // else teamPersons.add(checkingPerson);
                        }
                    }
                }

            }

        }
        Person coachTeam = teamSent.getCoach();
        if(coachTeam.getId()==0){
            Person cp = new Person();
            cp=coachTeam;
            boolean coachExsists = false;
            List<Person> coachAlready = personRepository.findByEmail(cp.getEmail());
            if(!coachAlready.isEmpty()){
                coachExsists=true;
                System.out.println("person hash working");
                coachTeam=coachAlready.get(0);
            }
            if(!coachExsists) {
                personRepository.save(cp);
                coachTeam=cp;
            }
        }
        HashSet<Person> uniquePersons = new HashSet<>();
        for(int hS=0; hS<teamPersons.size();hS++){
            if(uniquePersons.add(teamPersons.get(hS))==false){
                return FALSE;
            }
        }
        uniquePersons.add(coachTeam);
        if (uniquePersons.add(coachTeam) == false) {
            uniquePersons.remove(coachTeam);
            teamSent.setMembers(teamPersons);
            return TRUE;
        }
        return FALSE;
    }
    public boolean getAgeValidity(Team teamSent){
        List<Person> sentMembers = teamSent.getMembers();
        for(Person p: sentMembers){
            Optional<Person> findPerson = personRepository.findById(p.getId());
            if(findPerson.isPresent()){
                Person personIndB = findPerson.get();
                Integer memberAge = Period.between(personIndB.getDateofBirth(), LocalDate.now()).getYears();
                // System.out.println(memberAge);
                if(memberAge>24) {
                    return FALSE;
                }
            }
        }
        return TRUE;
    }
    public Team promotingTeams(Long contestId,Long teamId){
       Optional< Team> oldTeamFound = teamRepository.findById(teamId);
       Team oldTeam = new Team();
       if(oldTeamFound.isPresent())
         oldTeam = oldTeamFound.get();

       Team teamPromote = new Team();
       if(oldTeam.getRank()<=5 && oldTeam.getRank()>0) {
           if (checkUniqueness(contestId, oldTeam)) {
               if (getAgeValidity(oldTeam)) {
                   Optional<Contest> contestList = contestRepository.findById(contestId);
                   Contest contestQuery = contestList.get();
                   if (getContestCapacity(contestId)) {
                       if (contestQuery.isWriteEnable()) {
                           teamPromote.setName(oldTeam.getName());
                           teamPromote.setRank(0);
                           teamPromote.setState(Pending);
                           teamPromote.setCoach(oldTeam.getCoach());
                           List<Person> members = new ArrayList<>();
                           members.addAll(oldTeam.getMembers());
                           teamPromote.setMembers(members);
                           teamPromote.setClone(oldTeam);
                           teamPromote.setTeamWriteable(oldTeam.isTeamWriteable());
                           teamRepository.save(teamPromote);
                           List<Team> contestTeams = contestQuery.getTeams();
                           contestTeams.add(teamPromote);
                           contestQuery.setTeams(contestTeams);
                           contestRepository.save(contestQuery);
                           return teamPromote;
                       }
                   }
               }
           }
       }
        return teamPromote;
    }
    public Contest contestEdit(Contest contestSent){
        Contest searchContest = new Contest();
        Optional<Contest> foundContestOptional = contestRepository.findById(contestSent.getId());
        if(foundContestOptional.isPresent()){
             searchContest = foundContestOptional.get();
            if(searchContest.isWriteEnable()){
                if(contestSent.getCapacity()>=0){
                    searchContest.setCapacity(contestSent.getCapacity());
                }
                if(contestSent.getName()!=null){
                    searchContest.setName(contestSent.getName());
                }
                if(contestSent.getDate()!=null){
                    searchContest.setDate(searchContest.getDate());
                }
                if(contestSent.getTeams().size()>0){
                    List<Team> sentTeams = new ArrayList<>();
                    sentTeams.addAll(contestSent.getTeams());
                    searchContest.setTeams(sentTeams);
                    //searchContest.setTeams(contestSent.getTeams());
                }
                if(contestSent.getSubcontests()!=null){
                    List<Contest> sentSubs = new ArrayList<>();
                    sentSubs.addAll(contestSent.getSubcontests());
                    searchContest.setSubcontests(sentSubs);
                    //searchContest.setSubcontests(contestSent.getSubcontests());
                }
                if(contestSent.getManger().size()!=0){
                    searchContest.setManger(contestSent.getManger());
                }
                if(contestSent.getRegistration_from()!=null){
                    searchContest.setRegistration_from(contestSent.getRegistration_from());
                }
                if(contestSent.getRegistration_to()!=null){
                    searchContest.setRegistration_to(contestSent.getRegistration_to());
                }
                contestRepository.save(searchContest);
                return searchContest;
            }

        }
        return searchContest;
    }

    public Contest setEditable(Long contestId){
        Contest searchContest = new Contest();
        Optional<Contest> foundContestOptional = contestRepository.findById(contestId);
        if(foundContestOptional.isPresent()) {
            searchContest = foundContestOptional.get();
        }
        searchContest.setWriteEnable(TRUE);
        contestRepository.save(searchContest);
        return searchContest;
    }

    public Contest setReadble(Long contestId){
        Contest searchContest = new Contest();
        Optional<Contest> foundContestOptional = contestRepository.findById(contestId);
        if(foundContestOptional.isPresent()) {
            searchContest = foundContestOptional.get();
        }
        searchContest.setWriteEnable(FALSE);
        contestRepository.save(searchContest);
        return searchContest;
    }

}
