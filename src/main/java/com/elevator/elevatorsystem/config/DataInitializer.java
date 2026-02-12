package com.elevator.elevatorsystem.config;

import com.elevator.elevatorsystem.entity.Elevator;
import com.elevator.elevatorsystem.enums.Direction;
import com.elevator.elevatorsystem.enums.ElevatorState;
import com.elevator.elevatorsystem.repository.ElevatorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initElevators(ElevatorRepository elevatorRepository) {
        return args -> {

            

                for (int i = 0; i < 3; i++) {
                    Elevator elevator = new Elevator();
                    elevator.setCapacity(8);
                    elevator.setCurrentFloor(0);
                    elevator.setDirection(Direction.NONE);
                    elevator.setState(ElevatorState.IDLE);

                    elevatorRepository.save(elevator);
                }

                System.out.println("Default elevators created.");
            
        };
    }
}
