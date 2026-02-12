package com.elevator.elevatorsystem.entity;

import com.elevator.elevatorsystem.enums.Direction;
import com.elevator.elevatorsystem.enums.ElevatorState;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.io.Serializable;
@Entity
public class Elevator implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int currentFloor;
    private int targetFloor;

    @Enumerated(EnumType.STRING)
    private ElevatorState state;

    @Enumerated(EnumType.STRING)
    private Direction direction;

    private int capacity;

    private LocalDateTime lastMaintenance;

    public Elevator() {}

    public Long getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public ElevatorState getState() {
        return state;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getCapacity() {
        return capacity;
    }

    public LocalDateTime getLastMaintenance() {
        return lastMaintenance;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setLastMaintenance(LocalDateTime lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

}
