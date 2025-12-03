package com.city.services.mobility.repository;

import com.city.services.mobility.model.Schedule;
import com.city.services.mobility.model.TransportLine;
import com.city.services.mobility.model.TrafficStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransportRepository extends MongoRepository<TransportLine, String> {
    List<TransportLine> findByType(String type);
    Optional<TransportLine> findByLineNumber(String lineNumber);
    List<TransportLine> findByIsActiveTrue();
}

