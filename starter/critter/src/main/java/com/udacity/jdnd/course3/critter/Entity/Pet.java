package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.pet.PetType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.relational.core.mapping.Table;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long petId;

    private PetType type;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer owner;

    private LocalDate birthDate;

    private String notes;

    @ManyToMany(mappedBy = "pets")
    private List<Schedule> scheduleList;

    // Constructor
    public Pet(long petId, PetType type, String name, Customer owner, LocalDate birthDate) {
        this.petId = petId;
        this.type = type;
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
    }

    // Default constructor
    public Pet() {
    }

    // Getters and setters
    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
