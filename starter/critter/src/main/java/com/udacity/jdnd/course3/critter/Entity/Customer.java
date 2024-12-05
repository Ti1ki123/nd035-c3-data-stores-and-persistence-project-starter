package com.udacity.jdnd.course3.critter.Entity;

import jakarta.persistence.*;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Entity
public class Customer extends User {

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pet> pets;

    private String notes;

    public Customer() {}

    public Customer(Long id, String name, String phoneNumber, String username, String password, String address, List<Pet> pets, String notes) {
        super(id, name, phoneNumber, username, password, address);
        this.pets = pets;
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
