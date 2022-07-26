package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.dto.DepositResponse;
import com.nttdata.bootcamp.model.Deposit;
import com.nttdata.bootcamp.repository.DepositRepository;
import com.nttdata.bootcamp.service.impl.DepositImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepositService implements DepositImpl {

    @Autowired
    DepositRepository depositRepository;

    @Override
    public Flux<DepositResponse> getAllYanki() {
        return depositRepository.findAll().map(deposit -> DepositResponse.builder().amount(deposit.getAmount())
                .identityDocument(deposit.getIdentityDocument())
                .phoneNumber(deposit.getPhoneNumber())
                .phoneNumberDestination(deposit.getPhoneNumberDestination())
                .build());
    }

    @Override
    public Flux<DepositResponse> getPhoneYanki(String phoneNumber) {
        return depositRepository.findAll().filter(deposit -> deposit.getPhoneNumber().equals(phoneNumber))
                .map(deposit -> DepositResponse.builder().amount(deposit.getAmount())
                        .identityDocument(deposit.getIdentityDocument())
                        .phoneNumber(deposit.getPhoneNumber())
                        .phoneNumberDestination(deposit.getPhoneNumberDestination())
                        .build());
    }

    @Override
    public Mono<Deposit> createYanki(Deposit deposit) {
        return depositRepository.save(deposit);
    }
}
