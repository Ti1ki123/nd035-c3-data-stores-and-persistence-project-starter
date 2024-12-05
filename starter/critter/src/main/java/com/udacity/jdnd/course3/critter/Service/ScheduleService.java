package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import com.udacity.jdnd.course3.critter.Repositoty.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repositoty.EmployeeRepository;
import com.udacity.jdnd.course3.critter.Repositoty.PetRepository;
import com.udacity.jdnd.course3.critter.Repositoty.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Schedule save(Schedule schedule,List<Long> employeesIds, List<Long> petIds) {
        List<Employee> employees = employeesIds.stream()
                .map(employeeId -> {
                    Employee employee = new Employee();
                    Optional<Employee> optionalEmployee = employeeRepository.findByUserId(employeeId);
                    if (optionalEmployee.isEmpty()) {
                        throw new RuntimeException();
                    }
                    employee.setUserId(employeeId);

                    return employee;
                })
                .toList();

        List<Pet> pets = petIds.stream()
                .map(petId -> {
                    Pet pet = new Pet();
                    Optional<Pet> optionalPet = petRepository.findById(petId);
                    if (optionalPet.isEmpty()) {
                        throw new RuntimeException();
                    }
                    pet.setPetId(petId);
                    return pet;
                })
                .toList();

        schedule.setPets(pets);
        schedule.setEmployees(employees);


        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(Long petId) {

        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isEmpty()) {
            throw new RuntimeException();
        }

        List<Long> pets = new ArrayList<>();
        pets.add(petId);
        return scheduleRepository.findByPets(pets);
    }

    public List<Schedule> getScheduleForEmployee(Long userId) {

        Optional<Employee> optionalEmployee = employeeRepository.findByUserId(userId);
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException();
        }
        List<Long> employees = new ArrayList<>();
        employees.add(userId);
//        List<Long> petIds = pets.stream().map(Pet::getPetId).toList();
        return scheduleRepository.findByEmployees(employees);
    }

    public List<Schedule> getScheduleForCustomer(Long customerId) {

        List<Pet> pets = petRepository.findByOwnerUserId(customerId);
        if (pets == null || pets.isEmpty()) {
            throw new RuntimeException();
        }
        List<Long> petIds = pets.stream().map(Pet::getPetId).toList();
        return scheduleRepository.findByPets(petIds);
    }
}
