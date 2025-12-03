package com.city.services.mobility.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "traffic_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficStatus {

    @Id
    private String id;

    private String lineNumber;
    private TrafficLevel level;
    private String description; // "Accident", "Grève", etc.
    private LocalDateTime reportedAt;
    private LocalDateTime estimatedResolution;

    public enum TrafficLevel {
        FLUID,
        DENSE,
        CONGESTED,
        BLOCKED
    }
}