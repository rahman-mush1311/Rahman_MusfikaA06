package com.example.demo.service;

import com.example.demo.models.Contest;
import com.example.demo.models.Person;
import com.example.demo.models.Team;
import com.example.demo.repository.ContestRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TeamRepository;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    PersonRepository personRepository;

    @Autowired
    ContestRepository contestRepository;

    public boolean editTeam(Long teamId,Team teamSent){
        List<Contest> allContests =contestRepository.findAll();
        Team teamEdit = new Team();
        boolean constestEditable = false;
        for(Contest c: allContests){
            for(Team t: c.getTeams()){
                if(t.getId()==teamId){
                    if(c.isWriteEnable()) {
                        constestEditable = true;
                        break;
                    }
                    else
                        return false;
                }
            }
            break;
        }
        //team edit allowed
        if(constestEditable){
            Optional<Team> teamSearch = teamRepository.findById(teamId);
            if(teamSearch.isPresent()){
                teamEdit=teamSearch.get();
                List<Person> members = new ArrayList<>();
                members.addAll(teamSearch.get().getMembers());
                teamEdit.setMembers(members);

                if(teamSent.getName()!=null){
                    teamEdit.setName(teamSent.getName());
                }
                if(teamSent.getRank()!=0){
                    teamEdit.setRank(teamSent.getRank());
                }
                if(teamSent.getState()!=null){
                    teamEdit.setState(teamSent.getState());
                }
                if(teamSent.getClone()!=null){
                    teamEdit.setClone(teamSent.getClone());
                }
                if(teamSent.getCoach()!=null){
                    teamEdit.setCoach(teamSent.getCoach());
                }
                if(teamSent.getMembers().size()!=0){
                    teamEdit.setMembers(teamSent.getMembers());
                }
            }
            teamRepository.save(teamEdit);
        }
        return true;
    }
    public Team findById(Long teamId){
        Optional<Team> teamF = teamRepository.findById(teamId);
        Team sentTeam = new Team();
        if(teamF.isPresent()){
            sentTeam = teamF.get();
        }
        return sentTeam;

    }

}
