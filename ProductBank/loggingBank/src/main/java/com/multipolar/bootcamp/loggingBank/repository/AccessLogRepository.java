package com.multipolar.bootcamp.loggingBank.repository;

import com.multipolar.bootcamp.loggingBank.domain.AccessLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccessLogRepository extends MongoRepository<AccessLog, String> {
}
