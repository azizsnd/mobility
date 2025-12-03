package com.city.services.mobility.config;

import com.city.services.mobility.model.*;
import com.city.services.mobility.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            TransportRepository transportRepository,
            ScheduleRepository scheduleRepository,
            TrafficRepository trafficRepository) {

        return args -> {
            // Nettoyer les collections
            transportRepository.deleteAll();
            scheduleRepository.deleteAll();
            trafficRepository.deleteAll();

// Lignes de transport
            TransportLine line1 = new TransportLine(
                null, "L1", "Ligne 1 - Centre", TransportType.METRO,
                Arrays.asList("Gare Nord", "Centre Ville", "Université", "Hôpital"),
                "05:30-00:30", 5, true, "CityTrans", "#FF0000"
            );

            TransportLine line2 = new TransportLine(
                null, "B12", "Bus 12 - Banlieue", TransportType.BUS,
                Arrays.asList("Banlieue Est", "Centre Commercial", "Centre Ville"),
                "06:00-22:00", 15, true, "UrbanBus", "#00FF00"
            );

            transportRepository.saveAll(Arrays.asList(line1, line2));

// Horaires
            Schedule schedule1 = new Schedule(
                null, "L1", "Gare Nord",
                LocalDateTime.now().plusMinutes(10),
                LocalDateTime.now().plusMinutes(30),
                0, Schedule.Status.ON_TIME
            );

            Schedule schedule2 = new Schedule(
                null, "B12", "Banlieue Est",
                LocalDateTime.now().plusMinutes(5),
                LocalDateTime.now().plusMinutes(25),
                5, Schedule.Status.DELAYED
            );

            scheduleRepository.saveAll(Arrays.asList(schedule1, schedule2));

// État du trafic
            TrafficStatus traffic1 = new TrafficStatus(
                null, "L1", TrafficStatus.TrafficLevel.FLUID,
                "Trafic normal", LocalDateTime.now(),
                LocalDateTime.now().plusHours(1)
            );

            trafficRepository.save(traffic1);

            System.out.println("Données initiales insérées !");
};
}
}