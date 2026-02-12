package com.elevator.elevatorsystem.controller;

import com.elevator.elevatorsystem.entity.Elevator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.elevator.elevatorsystem.entity.ElevatorRequest;
import com.elevator.elevatorsystem.service.ElevatorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elevators")
public class ElevatorController {

    private final ElevatorService elevatorService;

    public ElevatorController(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }

    @PostMapping("/request")
    public Elevator requestElevator(@RequestBody ElevatorRequest request) {
        return elevatorService.requestElevator(request);
    }
    
    
    @GetMapping("/status")
    public List<Elevator> getStatus() {
        return elevatorService.getStatus();
    }
    
    @PostMapping("/simulate/{id}")
    public Elevator simulate(@PathVariable Long id) {
        return elevatorService.simulateMovement(id);
    }
    
    @PostMapping("/assign/{id}/{floor}")
    public Elevator manualAssign(
            @PathVariable Long id,
            @PathVariable int floor) {

        return elevatorService.manualAssign(id, floor);
    }
    
    @GetMapping("/requests")
    public List<ElevatorRequest> getRequests() {
        return elevatorService.getRequestHistory();
    }

    @GetMapping("/logs")
    public Page<ElevatorRequest> getLogs(Pageable pageable) {
        return elevatorService.getLogs(pageable);
    }
    
    @PutMapping("/{id}/repair")
    public Elevator repair(@PathVariable Long id) {
        return elevatorService.repairElevator(id);
    }

}
