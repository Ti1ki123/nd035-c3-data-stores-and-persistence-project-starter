package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Repositoty.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long userId) {

        Optional<Employee> returnFromRepo = employeeRepository.findByUserId(userId);

        if (returnFromRepo.isEmpty()) {
            throw new RuntimeException();
        }

        return returnFromRepo.get();
    }

//    public Employee updateEmployee(Employee employee) {
//
//        Optional<Employee> returnFromRepo = employeeRepository.findById(userId);
//
//        if (returnFromRepo.isEmpty()) {
//            throw new RuntimeException();
//        }
//
//        return returnFromRepo.get();
//    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {

        Employee employeeFromRepo = getEmployee(employeeId);

        employeeFromRepo.setDaysAvailable(daysAvailable);

        employeeRepository.save(employeeFromRepo);

    }

    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date) {
        List<String> skillsString = new ArrayList<>();

        for (EmployeeSkill e : skills) {
            skillsString.add(e.name());
        }
        List<Long> returnvalue =  employeeRepository.findEmployeesForService(skillsString,date.getDayOfWeek().toString(),skills.size());
        return employeeRepository.findAllById(returnvalue);
    }
}
