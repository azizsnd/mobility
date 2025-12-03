package com.city.services.mobility.repository;

import com.city.services.mobility.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    List<Schedule> findByLineNumber(String lineNumber);
    List<Schedule> findByLineNumberAndStation(String lineNumber, String station);
    List<Schedule> findByStatus(Schedule.Status status);
}
