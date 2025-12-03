package com.city.services.mobility.service;

import com.city.services.mobility.model.Schedule;
import com.city.services.mobility.model.TransportLine;
import com.city.services.mobility.model.TrafficStatus;
import com.city.services.mobility.repository.ScheduleRepository;
import com.city.services.mobility.repository.TransportRepository;
import com.city.services.mobility.repository.TrafficRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransportService {

    private final TransportRepository transportRepository;
    private final ScheduleRepository scheduleRepository;
    private final TrafficRepository trafficRepository;

    // Gestion des lignes de transport
    public List<TransportLine> getAllLines() {
        return transportRepository.findAll();
    }

    public Optional<TransportLine> getLineByNumber(String lineNumber) {
        return transportRepository.findByLineNumber(lineNumber);
    }

    public List<TransportLine> getLinesByType(String type) {
        return transportRepository.findByType(type);
    }

    public TransportLine createLine(TransportLine line) {
        return transportRepository.save(line);
    }

    public TransportLine updateLine(String id, TransportLine line) {
        line.setId(id);
        return transportRepository.save(line);
    }

    public void deleteLine(String id) {
        transportRepository.deleteById(id);
    }

    // Gestion des horaires
    public List<Schedule> getSchedulesByLine(String lineNumber) {
        return scheduleRepository.findByLineNumber(lineNumber);
    }

    public List<Schedule> getSchedulesByLineAndStation(String lineNumber, String station) {
        return scheduleRepository.findByLineNumberAndStation(lineNumber, station);
    }

    public List<Schedule> getDelayedSchedules() {
        return scheduleRepository.findByStatus(Schedule.Status.DELAYED);
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // Gestion du trafic
    public List<TrafficStatus> getTrafficStatusByLine(String lineNumber) {
        return trafficRepository.findByLineNumber(lineNumber);
    }

    public List<TrafficStatus> getCurrentIssues() {
        return trafficRepository.findByLevel(TrafficStatus.TrafficLevel.CONGESTED);
    }

    public TrafficStatus reportTrafficIssue(TrafficStatus status) {
        return trafficRepository.save(status);
    }

    // Services avancés
    public List<TransportLine> findAlternativeRoutes(String from, String to) {
        // Logique de recherche d'alternatives
        // (À implémenter avec une logique de graphe)
        return transportRepository.findByIsActiveTrue();
    }

    public String getEstimatedTravelTime(String lineNumber, String fromStation, String toStation) {
        // Calcul du temps de trajet estimé
        // (À implémenter)
        return "20 minutes";
    }
}