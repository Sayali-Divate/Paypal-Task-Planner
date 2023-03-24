package com.taskplanner.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskplanner.demo.entity.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

}
