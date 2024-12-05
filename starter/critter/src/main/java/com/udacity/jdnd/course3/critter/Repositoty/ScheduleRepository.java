package com.udacity.jdnd.course3.critter.Repositoty;

import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "select s1_0.schedule_id,s1_0.activities,s1_0.date from schedule s1_0 left join schedule_pet p1_0 on s1_0.schedule_id=p1_0.schedule_id where p1_0.pet_id in (:petIds)",nativeQuery = true)
    List<Schedule> findByPets(List<Long> petIds);

    @Query(value = "select s1_0.schedule_id,s1_0.activities,s1_0.date from schedule s1_0 left join schedule_employee p1_0 on s1_0.schedule_id=p1_0.schedule_id where p1_0.user_id in (:employeeIds)",nativeQuery = true)
    List<Schedule> findByEmployees(List<Long> employeeIds);
}
