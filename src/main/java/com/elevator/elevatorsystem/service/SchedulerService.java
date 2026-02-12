package com.elevator.elevatorsystem.service;

import com.elevator.elevatorsystem.entity.Elevator;
import com.elevator.elevatorsystem.enums.ElevatorState;
import com.elevator.elevatorsystem.repository.ElevatorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class SchedulerService {

    private static final Logger log =
            LoggerFactory.getLogger(SchedulerService.class);

    private final ElevatorRepository elevatorRepository;

    public SchedulerService(ElevatorRepository elevatorRepository) {
        this.elevatorRepository = elevatorRepository;
    }

    public Elevator findNearestElevator(int floor) {

        List<Elevator> elevators = elevatorRepository.findAll();

        if (elevators.isEmpty()) {
            throw new RuntimeException("No elevators found in DB");
        }

        PriorityQueue<Elevator> pq = new PriorityQueue<>(
                Comparator.comparingInt(
                        e -> Math.abs(e.getCurrentFloor() - floor))
        );

        for (Elevator elevator : elevators) {
            if (elevator.getState() != ElevatorState.MAINTENANCE) {
                pq.offer(elevator);
            }
        }

        if (pq.isEmpty()) {
            throw new RuntimeException("No available elevators");
        }

        return pq.poll();
    }

}
