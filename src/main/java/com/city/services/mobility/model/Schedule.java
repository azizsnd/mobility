package com.city.services.mobility.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    private String id;

    private String lineNumber;
    private String station;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer estimatedDelay; // en minutes
    private Status status;

    public enum Status {
        ON_TIME,
        DELAYED,
        CANCELLED,
        ARRIVED
    }
}