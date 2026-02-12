package com.elevator.elevatorsystem.repository;

import com.elevator.elevatorsystem.entity.Elevator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElevatorRepository extends JpaRepository<Elevator, Long> {
}
