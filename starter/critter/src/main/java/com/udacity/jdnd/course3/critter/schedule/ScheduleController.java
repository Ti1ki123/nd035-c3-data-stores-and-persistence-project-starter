package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import com.udacity.jdnd.course3.critter.Entity.User;
import com.udacity.jdnd.course3.critter.Service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

        Schedule scheduleInput = new Schedule();
        BeanUtils.copyProperties(scheduleDTO,scheduleInput);
//        scheduleInput.setScheduleId(null);
//        scheduleInput

        Schedule scheduleSaved = scheduleService.save(scheduleInput,scheduleDTO.getEmployeeIds(),scheduleDTO.getPetIds());

        ScheduleDTO returnValue = new ScheduleDTO();

        BeanUtils.copyProperties(scheduleSaved,returnValue);
        returnValue.setEmployeeIds(scheduleDTO.getEmployeeIds());
        returnValue.setPetIds(scheduleDTO.getPetIds());

        return returnValue;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {

        List<Schedule> scheduleList = scheduleService.findAll();


        return scheduleList.stream()
                .map(this::convertScheduleDTO)
                .toList();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

        List<Schedule> scheduleList = scheduleService.getScheduleForPet(petId);


        return scheduleList.stream()
                .map(this::convertScheduleDTO)
                .toList();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> scheduleList = scheduleService.getScheduleForEmployee(employeeId);


        return scheduleList.stream()
                .map(this::convertScheduleDTO)
                .toList();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleList = scheduleService.getScheduleForCustomer(customerId);

        return scheduleList.stream()
                .map(this::convertScheduleDTO)
                .toList();
    }



    public ScheduleDTO convertScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();

        BeanUtils.copyProperties(schedule,scheduleDTO);
        List<Long> employees = schedule.getEmployees().stream()
                .map(User::getUserId)
                .toList();

        List<Long> pets = schedule.getPets().stream()
                .map(Pet::getPetId)
                .toList();
        scheduleDTO.setPetIds(pets);
        scheduleDTO.setEmployeeIds(employees);
        return scheduleDTO;
    }
}
