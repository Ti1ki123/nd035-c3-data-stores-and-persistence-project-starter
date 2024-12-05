package com.udacity.jdnd.course3.critter.Entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import jakarta.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
public class Employee extends User {

    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING) // Store enum as string in the database
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    @Enumerated(EnumType.STRING) // Store enum as string in the database
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Schedule> scheduleList;

    // Default constructor
    public Employee() {
//        super(); // Call the default constructor of User
    }

    // Parameterized constructor
    public Employee(Long id, String name, String phoneNumber, String username, String password, String address,
                    Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable, List<Schedule> scheduleList) {
        super(id, name, phoneNumber, username, password, address); // Initialize fields from User
        this.skills = skills;
        this.daysAvailable = daysAvailable;
        this.scheduleList = scheduleList;
    }

    // Getters and Setters
    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
