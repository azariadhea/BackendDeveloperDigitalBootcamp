package com.multipolar.bootcamp.loggingAccount.repository;

import com.multipolar.bootcamp.loggingAccount.domain.AccessLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccessLogRepository extends MongoRepository<AccessLog, String> {
}
