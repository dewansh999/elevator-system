package com.elevator.elevatorsystem.repository;

import com.elevator.elevatorsystem.entity.ElevatorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElevatorRequestRepository
        extends JpaRepository<ElevatorRequest, Long> {

    Page<ElevatorRequest> findAll(Pageable pageable);
}
