package com.example.demo.repository;

import com.example.demo.models.Contest;
import com.example.demo.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface ContestRepository extends JpaRepository<Contest,Long> {

   List<Contest> findByName(String name);
   List<Contest> findAllByTeams(Team team);


  }
