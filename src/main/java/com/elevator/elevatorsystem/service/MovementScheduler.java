package com.elevator.elevatorsystem.service;

import com.elevator.elevatorsystem.entity.Elevator;
import com.elevator.elevatorsystem.enums.Direction;
import com.elevator.elevatorsystem.enums.ElevatorState;
import com.elevator.elevatorsystem.repository.ElevatorRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovementScheduler {

    private final ElevatorRepository elevatorRepository;

    public MovementScheduler(ElevatorRepository elevatorRepository) {
        this.elevatorRepository = elevatorRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void moveElevators() {

        List<Elevator> elevators = elevatorRepository.findAll();

        for (Elevator elevator : elevators) {

            if (elevator.getState() == ElevatorState.MOVING) {

                if (elevator.getCurrentFloor() < elevator.getTargetFloor()) {
                    elevator.setCurrentFloor(elevator.getCurrentFloor() + 1);
                    elevator.setDirection(Direction.UP);

                } else if (elevator.getCurrentFloor() > elevator.getTargetFloor()) {
                    elevator.setCurrentFloor(elevator.getCurrentFloor() - 1);
                    elevator.setDirection(Direction.DOWN);
                }

                if (elevator.getCurrentFloor() == elevator.getTargetFloor()) {
                    elevator.setState(ElevatorState.IDLE);
                    elevator.setDirection(Direction.NONE);
                }

                elevatorRepository.save(elevator);
            }
        }
    }
}
