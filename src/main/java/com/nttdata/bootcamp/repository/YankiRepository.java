package com.nttdata.bootcamp.repository;

import com.nttdata.bootcamp.model.Yanki;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YankiRepository extends ReactiveCrudRepository<Yanki, String> {
}
