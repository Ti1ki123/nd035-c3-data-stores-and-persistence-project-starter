package com.udacity.jdnd.course3.critter.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.MatchesPattern;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Nationalized
    private String name;

    @Column(length = 30)
//    @MatchesPattern(value = "^[0-9]*$")
    private String phoneNumber;

    private String username;

    private String password;

    @Nationalized
    private String address;

    public User() {}

    public User(Long userId, String name, String phoneNumber, String username, String password, String address) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.address = address;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
