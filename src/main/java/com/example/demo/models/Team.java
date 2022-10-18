package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int rank;
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToMany
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    private List<Person> members = new ArrayList<>();

    @OneToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    @Nullable
    private Person coach;

    @OneToOne
    @Nullable
    private Team clone;



    @Column
    @Nullable
    private boolean teamWriteable;



     /* public Person getCoach() {
        return coach;
    }

    public void setCoach(Person coach) {
        this.coach = coach;
    }

    public Set<Person> getMembers() {
        return members;
    }

    public void setMembers(Set<Person> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Nullable
    public Team getClone() {
        return clone;
    }

    public void setClone(@Nullable Team clone) {
        this.clone = clone;
    }

    public boolean isTeamWriteable() {
        return teamWriteable;
    }

    public void setTeamWriteable(boolean teamWriteable) {
        this.teamWriteable = teamWriteable;
    }*/
    public enum State {
        Accepted, Pending, Cancelled;
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();

    }

}
