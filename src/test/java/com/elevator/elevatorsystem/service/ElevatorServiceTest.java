package com.elevator.elevatorsystem.service;

import com.elevator.elevatorsystem.entity.Elevator;
import com.elevator.elevatorsystem.entity.ElevatorRequest;
import com.elevator.elevatorsystem.enums.ElevatorState;
import com.elevator.elevatorsystem.repository.ElevatorRepository;
import com.elevator.elevatorsystem.repository.ElevatorRequestRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class ElevatorServiceTest {

    @Test
    void requestElevator_shouldAssignElevator() {

        ElevatorRepository elevatorRepository = Mockito.mock(ElevatorRepository.class);
        ElevatorRequestRepository requestRepository = Mockito.mock(ElevatorRequestRepository.class);
        SchedulerService schedulerService = Mockito.mock(SchedulerService.class);

        ElevatorService service =
                new ElevatorService(elevatorRepository, requestRepository, schedulerService);

        Elevator elevator = new Elevator();
        elevator.setId(1L);
        elevator.setCurrentFloor(0);
        elevator.setState(ElevatorState.IDLE);

        ElevatorRequest request = new ElevatorRequest();

        Mockito.when(schedulerService.findNearestElevator(Mockito.anyInt()))
                .thenReturn(elevator);

        Mockito.when(elevatorRepository.save(Mockito.any()))
                .thenReturn(elevator);

        Elevator result = service.requestElevator(request);

        assertNotNull(result);
        assertEquals(ElevatorState.MOVING, result.getState());
    }
    
    @Test
    void simulateMovement_shouldMoveElevatorUp() {

        ElevatorRepository elevatorRepository = Mockito.mock(ElevatorRepository.class);
        ElevatorRequestRepository requestRepository = Mockito.mock(ElevatorRequestRepository.class);
        SchedulerService schedulerService = Mockito.mock(SchedulerService.class);

        ElevatorService service =
                new ElevatorService(elevatorRepository, requestRepository, schedulerService);

        Elevator elevator = new Elevator();
        elevator.setId(1L);
        elevator.setCurrentFloor(2);
        elevator.setTargetFloor(5);

        Mockito.when(elevatorRepository.findById(1L))
                .thenReturn(java.util.Optional.of(elevator));

        Mockito.when(elevatorRepository.save(Mockito.any()))
                .thenReturn(elevator);

        Elevator result = service.simulateMovement(1L);

        assertEquals(3, result.getCurrentFloor());
    }

    @Test
    void manualAssign_shouldUpdateTargetFloor() {

        ElevatorRepository elevatorRepository = Mockito.mock(ElevatorRepository.class);
        ElevatorRequestRepository requestRepository = Mockito.mock(ElevatorRequestRepository.class);
        SchedulerService schedulerService = Mockito.mock(SchedulerService.class);

        ElevatorService service =
                new ElevatorService(elevatorRepository, requestRepository, schedulerService);

        Elevator elevator = new Elevator();
        elevator.setId(1L);
        elevator.setCurrentFloor(1);

        Mockito.when(elevatorRepository.findById(1L))
                .thenReturn(java.util.Optional.of(elevator));

        Mockito.when(elevatorRepository.save(Mockito.any()))
                .thenReturn(elevator);

        Elevator result = service.manualAssign(1L, 10);

        assertEquals(10, result.getTargetFloor());
    }

}
