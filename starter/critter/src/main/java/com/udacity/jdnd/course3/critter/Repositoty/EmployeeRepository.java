package com.udacity.jdnd.course3.critter.Repositoty;

import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

    @Query(value = "select e.user_id from employee e " +
            " inner join employee_skills es on e.user_id = es.employee_user_id " +
            " inner join employee_days_available eda on e.user_id  = eda.employee_user_id " +
            " where es.skills in (:skills) and eda.days_available = :availableDate " +
            " Group by e.user_id HAVING COUNT(DISTINCT es.skills) = :requiredSkillCount",
    nativeQuery = true)
    List<Long> findEmployeesForService( @Param("skills") List<String> skills,
                                            @Param("availableDate") String availableDate,
                                            @Param("requiredSkillCount") int requiredSkillCount);

    Optional<Employee> findByUserId(Long userId);
}
