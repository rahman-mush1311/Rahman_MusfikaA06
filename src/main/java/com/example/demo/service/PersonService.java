package com.example.demo.service;

import com.example.demo.models.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    public void populatePerson() {

        Person person = new Person();
        person.setEmail("bob@gmail.com");
        person.setName("Bob");
        person.setUniversity("Baylor");
        person.setDateofBirth(LocalDate.of(
                1988, 9, 18
        ));

        personRepository.save(person);

        Person person00 = new Person();
        person00.setEmail("joe@gmail.com");
        person00.setName("Joe");
        person00.setUniversity("Baylor");
        person00.setDateofBirth(LocalDate.of(
                1987, 8, 28
        ));

        personRepository.save(person00);

        Person person01 = new Person();
        person01.setEmail("lenord@gmail.com");
        person01.setName("Lenord");
        person01.setUniversity("Baylor");
        person01.setDateofBirth(LocalDate.of(
                2005, 5, 28
        ));

        personRepository.save(person01);

        Person person02 = new Person();
        person02.setEmail("penny@gmail.com");
        person02.setName("Penny");
        person02.setUniversity("Baylor");
        person02.setDateofBirth(LocalDate.of(
                2000, 4, 15
        ));

        personRepository.save(person02);

        Person person03 = new Person();
        person03.setEmail("sheldon@gmail.com");
        person03.setName("Sheldon");
        person03.setUniversity("Baylor");
        person03.setDateofBirth(LocalDate.of(
                1999, 1, 5
        ));

        personRepository.save(person03);

        Person person04 = new Person();
        person04.setEmail("bill@gmail.com");
        person04.setName("Bill");
        person04.setUniversity("Baylor");
        person04.setDateofBirth(LocalDate.of(
                1989, 10, 25
        ));

        personRepository.save(person04);

        Person person05 = new Person();
        person05.setEmail("raj@gmail.com");
        person05.setName("Raj");
        person05.setUniversity("CalTech");
        person05.setDateofBirth(LocalDate.of(
                2000, 1, 5
        ));
        personRepository.save(person05);

        Person person06 = new Person();
        person06.setEmail("bernedatte@gmail.com");
        person06.setName("Bernadette");
        person06.setUniversity("CalTech");
        person06.setDateofBirth(LocalDate.of(
                2000, 11, 13
        ));

        personRepository.save(person06);

        Person person07 = new Person();
        person07.setEmail("amy@gmail.com");
        person07.setName("Amy");
        person07.setUniversity("CalTech");
        person07.setDateofBirth(LocalDate.of(
                2002, 3, 19
        ));

        personRepository.save(person07);

        Person person08 = new Person();
        person08.setEmail("stuart@gmail.com");
        person08.setName("Stuart");
        person08.setUniversity("CalTech");
        person08.setDateofBirth(LocalDate.of(
                1987, 6, 12
        ));

        personRepository.save(person08);

        //businessRule data insert
        Person person09 = new Person();
        person09.setEmail("micheal@gmail.com");
        person09.setName("Micheal");
        person09.setUniversity("CalTech");
        person09.setDateofBirth(LocalDate.of(
                1988, 9, 18
        ));

        personRepository.save(person09);

    }

    public List<Person> getPersonbyId(Long id){

        List<Person> personsAll=new ArrayList<>();
        Optional<Person> persons=personRepository.findById(id);
        if(persons.isPresent()) {
            Person existingPerson = persons.get();
            personsAll.add(existingPerson);

        }
        return personsAll;
    }

    public void createPerson(Person personSent){
        Person p1 = personSent;
        personRepository.save(p1);
    }

}
