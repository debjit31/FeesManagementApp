package com.fees.management.app.repository;

import com.fees.management.app.entity.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transactions, String> {
}
