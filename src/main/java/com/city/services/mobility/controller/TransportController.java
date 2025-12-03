package com.city.services.mobility.controller;

import com.city.services.mobility.model.Schedule;
import com.city.services.mobility.model.TransportLine;
import com.city.services.mobility.model.TrafficStatus;
import com.city.services.mobility.service.TransportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transport")
@RequiredArgsConstructor
@Tag(name = "Transport API", description = "API pour la mobilité intelligente")
public class TransportController {

    private final TransportService transportService;

    // === Lignes de transport ===

    @GetMapping("/lines")
    @Operation(summary = "Get all transport lines")
    public ResponseEntity<List<TransportLine>> getAllLines() {
        return ResponseEntity.ok(transportService.getAllLines());
    }

    @GetMapping("/lines/{lineNumber}")
    @Operation(summary = "Get line by number")
    public ResponseEntity<TransportLine> getLineByNumber(@PathVariable String lineNumber) {
        return transportService.getLineByNumber(lineNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/lines")
    @Operation(summary = "Create a new transport line")
    public ResponseEntity<TransportLine> createLine(@RequestBody TransportLine line) {
        return ResponseEntity.ok(transportService.createLine(line));
    }

    @PutMapping("/lines/{id}")
    @Operation(summary = "Update a transport line")
    public ResponseEntity<TransportLine> updateLine(
            @PathVariable String id,
            @RequestBody TransportLine line) {
        return ResponseEntity.ok(transportService.updateLine(id, line));
    }

    @DeleteMapping("/lines/{id}")
    @Operation(summary = "Delete a transport line")
    public ResponseEntity<Void> deleteLine(@PathVariable String id) {
        transportService.deleteLine(id);
        return ResponseEntity.noContent().build();
    }

    // === Horaires ===

    @GetMapping("/schedules/{lineNumber}")
    @Operation(summary = "Get schedules for a line")
    public ResponseEntity<List<Schedule>> getSchedules(@PathVariable String lineNumber) {
        return ResponseEntity.ok(transportService.getSchedulesByLine(lineNumber));
    }

    @GetMapping("/schedules/{lineNumber}/{station}")
    @Operation(summary = "Get schedules for a specific station")
    public ResponseEntity<List<Schedule>> getSchedulesByStation(
            @PathVariable String lineNumber,
            @PathVariable String station) {
        return ResponseEntity.ok(transportService.getSchedulesByLineAndStation(lineNumber, station));
    }

    @GetMapping("/schedules/delayed")
    @Operation(summary = "Get all delayed schedules")
    public ResponseEntity<List<Schedule>> getDelayedSchedules() {
        return ResponseEntity.ok(transportService.getDelayedSchedules());
    }

    // === État du trafic ===

    @GetMapping("/traffic/{lineNumber}")
    @Operation(summary = "Get traffic status for a line")
    public ResponseEntity<List<TrafficStatus>> getTrafficStatus(@PathVariable String lineNumber) {
        return ResponseEntity.ok(transportService.getTrafficStatusByLine(lineNumber));
    }

    @GetMapping("/traffic/issues")
    @Operation(summary = "Get current traffic issues")
    public ResponseEntity<List<TrafficStatus>> getCurrentIssues() {
        return ResponseEntity.ok(transportService.getCurrentIssues());
    }

    // === Services avancés ===

    @GetMapping("/alternatives")
    @Operation(summary = "Find alternative routes")
    public ResponseEntity<List<TransportLine>> findAlternativeRoutes(
            @RequestParam String from,
            @RequestParam String to) {
        return ResponseEntity.ok(transportService.findAlternativeRoutes(from, to));
    }

    @GetMapping("/estimate")
    @Operation(summary = "Get estimated travel time")
    public ResponseEntity<String> getEstimatedTravelTime(
            @RequestParam String lineNumber,
            @RequestParam String fromStation,
            @RequestParam String toStation) {
        return ResponseEntity.ok(transportService.getEstimatedTravelTime(lineNumber, fromStation, toStation));
    }
}