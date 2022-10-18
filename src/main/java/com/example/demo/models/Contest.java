package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contest {

    @Id
   // @SequenceGenerator(name= "CONTEST_SEQUENCE", sequenceName = "CONTEST_SEQUENCE_ID", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private int capacity;
    @Column
    private LocalDate date;
    @Column
    private String name;
    @Column
    private boolean registration_allowed;
    @Column
    private LocalDate registration_from;
    @Column
    private LocalDate registration_to;

    /*@ManyToOne
    private Contest prelim;*/
    @OneToMany
    @Nullable
    private List<Contest> subcontests;
    @OneToMany(fetch = FetchType.LAZY)
    @Nullable
    private List <Team> teams = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Contest_Manager",
            joinColumns = {@JoinColumn(name = "CONTEST_ID",referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PERSON_ID",referencedColumnName = "ID")})
    private Set<Person> manger = new HashSet<>();

    @Column
    private boolean writeEnable;



  /*  public boolean isReadEnable() {
        return readEnable;
    }

    public void setReadEnable(boolean readEnable) {
        this.readEnable = readEnable;
    }

    public List<Contest> getSubcontests() {
        return subcontests;
    }

    public void setSubcontests(List<Contest> subcontests) {
        this.subcontests = subcontests;
    }

    public Set<Person> getManger() {
        return manger;
    }

    public void setManger(Set<Person> manger) {
        this.manger = manger;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRegistration_allowed() {
        return registration_allowed;
    }

    public void setRegistration_allowed(boolean registration_allowed) {
        this.registration_allowed = registration_allowed;
    }


    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public boolean isWriteEnable() {
        return writeEnable;
    }

    public void setWriteEnable(boolean writeEnable) {
        this.writeEnable = writeEnable;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getRegistration_from() {
        return registration_from;
    }

    public void setRegistration_from(LocalDate registration_from) {
        this.registration_from = registration_from;
    }

    public LocalDate getRegistration_to() {
        return registration_to;
    }

    public void setRegistration_to(LocalDate registration_to) {
        this.registration_to = registration_to;
    }*/
}
