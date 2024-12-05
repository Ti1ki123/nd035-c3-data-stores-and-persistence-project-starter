package com.udacity.jdnd.course3.critter.Repositoty;

import com.udacity.jdnd.course3.critter.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
