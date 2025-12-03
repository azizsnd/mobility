package com.city.services.mobility.repository;

import com.city.services.mobility.model.TrafficStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrafficRepository extends MongoRepository<TrafficStatus, String> {
    List<TrafficStatus> findByLineNumber(String lineNumber);
    List<TrafficStatus> findByLevel(TrafficStatus.TrafficLevel level);
}
