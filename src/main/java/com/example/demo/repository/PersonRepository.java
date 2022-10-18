package com.example.demo.repository;

import com.example.demo.models.Contest;
import com.example.demo.models.Person;
import com.example.demo.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Boolean.TRUE;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {
      List<Person> findByEmail(String email);

}
