package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Deposit;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends ReactiveCrudRepository<Deposit, String> {
}
