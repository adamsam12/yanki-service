package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.dto.DepositResponse;
import com.nttdata.bootcamp.model.Deposit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepositImpl {
    Flux<DepositResponse> getPhoneYanki(String phoneNumber);
    Mono<Deposit> createYanki(Deposit deposit);
    Flux<DepositResponse> getAllYanki();
}
