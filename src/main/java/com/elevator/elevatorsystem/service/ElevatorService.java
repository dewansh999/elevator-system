package com.elevator.elevatorsystem.service;

import com.elevator.elevatorsystem.entity.Elevator;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.elevator.elevatorsystem.entity.ElevatorRequest;
import com.elevator.elevatorsystem.enums.Direction;
import com.elevator.elevatorsystem.enums.ElevatorState;
import com.elevator.elevatorsystem.repository.ElevatorRepository;
import com.elevator.elevatorsystem.repository.ElevatorRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ElevatorService {

    private static final Logger log = LoggerFactory.getLogger(ElevatorService.class);

    private final ElevatorRepository elevatorRepository;
    private final ElevatorRequestRepository requestRepository;
    private final SchedulerService schedulerService;

    public ElevatorService(
            ElevatorRepository elevatorRepository,
            ElevatorRequestRepository requestRepository,
            SchedulerService schedulerService) {

        this.elevatorRepository = elevatorRepository;
        this.requestRepository = requestRepository;
        this.schedulerService = schedulerService;
    }

    
    @CacheEvict(value = "elevatorStatus", allEntries = true)
    public Elevator requestElevator(ElevatorRequest request) {

        log.info("Received elevator request from floor {} to {}",
                request.getRequestFloor(),
                request.getDestinationFloor());

        Elevator elevator =
                schedulerService.findNearestElevator(request.getRequestFloor());

        log.info("Elevator {} assigned", elevator.getId());

        request.setRequestTime(LocalDateTime.now());
        request.setElevatorId(elevator.getId());

        requestRepository.save(request);

        elevator.setTargetFloor(request.getDestinationFloor());
        elevator.setState(ElevatorState.MOVING);

        return elevatorRepository.save(elevator);
    }
    
    @Cacheable("elevatorStatus")
    public List<Elevator> getStatus() {
        log.info("Fetching elevator status");
        return elevatorRepository.findAll();
    }

    
    @CacheEvict(value = "elevatorStatus", allEntries = true)
    public Elevator simulateMovement(Long elevatorId) {

        log.info("Simulating movement for elevator {}", elevatorId);

        Elevator elevator = elevatorRepository.findById(elevatorId)
                .orElseThrow(() -> new RuntimeException("Elevator not found"));

        if (elevator.getCurrentFloor() < elevator.getTargetFloor()) {
            elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
        } else if (elevator.getCurrentFloor() > elevator.getTargetFloor()) {
            elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
        }

        if (elevator.getCurrentFloor() == elevator.getTargetFloor()) {
            log.info("Elevator {} reached destination floor {}",
                    elevator.getId(),
                    elevator.getTargetFloor());

            elevator.setState(ElevatorState.IDLE);
            elevator.setDirection(Direction.NONE);
        }

        return elevatorRepository.save(elevator);
    }

    
    @CacheEvict(value = "elevatorStatus", allEntries = true)
    public Elevator manualAssign(Long elevatorId, int targetFloor) {

        log.info("Manual assignment: elevator {} → floor {}", elevatorId, targetFloor);

        Elevator elevator = elevatorRepository.findById(elevatorId)
                .orElseThrow(() -> new RuntimeException("Elevator not found"));

        elevator.setTargetFloor(targetFloor);
        elevator.setState(ElevatorState.MOVING);

        if (elevator.getCurrentFloor() < targetFloor) {
            elevator.setDirection(Direction.UP);
        } else if (elevator.getCurrentFloor() > targetFloor) {
            elevator.setDirection(Direction.DOWN);
        } else {
            elevator.setDirection(Direction.NONE);
        }

        return elevatorRepository.save(elevator);
    }
    
    public List<ElevatorRequest> getRequestHistory() {
        return requestRepository.findAll();
    }
    
    public Page<ElevatorRequest> getLogs(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }
    
    public Elevator repairElevator(Long elevatorId) {

        Elevator elevator = elevatorRepository.findById(elevatorId)
                .orElseThrow(() -> new RuntimeException("Elevator not found"));

        elevator.setState(ElevatorState.IDLE);
        elevator.setDirection(Direction.NONE);
        elevator.setLastMaintenance(java.time.LocalDateTime.now());

        return elevatorRepository.save(elevator);
    }


}
