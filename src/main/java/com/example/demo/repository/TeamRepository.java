package com.example.demo.repository;

import com.example.demo.models.Person;
import com.example.demo.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    public List<Team> findAll();

   public List <Team> findByMembers(Person p);


}
