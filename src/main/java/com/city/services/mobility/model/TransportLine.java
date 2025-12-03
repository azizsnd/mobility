package com.city.services.mobility.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "transport_lines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportLine {

    @Id
    private String id;

    private String lineNumber;
    private String name;
    private TransportType type;
    private List<String> stations;
    private String operatingHours; // "06:00-22:00"
    private Integer frequency; // en minutes
    private Boolean isActive;

    // Informations complémentaires
    private String operator;
    private String colorCode; // Pour la carte
}