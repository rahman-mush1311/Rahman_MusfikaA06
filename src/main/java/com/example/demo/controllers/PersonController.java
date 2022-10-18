package com.example.demo.controllers;

import com.example.demo.models.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @RequestMapping(value = "/populate", method = RequestMethod.POST)
    public ResponseEntity populate(){
       personService.populatePerson();
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public ResponseEntity<Person> getAllPersons(){
        return new ResponseEntity(personRepository.findAll(),HttpStatus.OK);
    }

    @RequestMapping(value = "/createperson", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody Person personSent)
    {
        personService.createPerson(personSent);
        return new ResponseEntity(HttpStatus.OK);
    }

  }
