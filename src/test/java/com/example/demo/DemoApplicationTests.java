package com.example.demo;

import com.example.demo.models.Contest;
import com.example.demo.models.Person;
import com.example.demo.models.Team;
import com.example.demo.repository.ContestRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class)
@DataJpaTest
class DemoApplicationTests {
   // @Autowired
   // private TestEntityManager entityManager;
    @Autowired
    PersonRepository personRepository;

    @Autowired
    ContestRepository contestRepository;
    @Autowired
    TeamRepository teamRepository;
  // @Autowired
    //SuperRepository superRepository;
    public void testPopulate(){


    }
    @Test
    public void testPopulateResults() {
    }

}
