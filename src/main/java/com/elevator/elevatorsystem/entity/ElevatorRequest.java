package com.elevator.elevatorsystem.entity;

import com.elevator.elevatorsystem.enums.Direction;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.io.Serializable;
@Entity
public class ElevatorRequest implements Serializable{

	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int requestFloor;
    private int destinationFloor;

    @Enumerated(EnumType.STRING)
    private Direction direction;

    private LocalDateTime requestTime;

    private Long elevatorId;

    // --- getters ---
    public Long getId() {
        return id;
    }

    public int getRequestFloor() {
        return requestFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public Long getElevatorId() {
        return elevatorId;
    }

    // --- setters ---
    public void setRequestFloor(int requestFloor) {
        this.requestFloor = requestFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public void setElevatorId(Long elevatorId) {
        this.elevatorId = elevatorId;
    }
}
