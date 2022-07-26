package com.nttdata.bootcamp.service;

import com.nttdata.bootcamp.dto.YankiResponse;
import com.nttdata.bootcamp.model.Yanki;
import com.nttdata.bootcamp.repository.YankiRepository;
import com.nttdata.bootcamp.service.impl.YankiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class YankiService implements YankiServiceImpl {

    @Autowired
    YankiRepository yankiRepository;

    @Override
    public Flux<YankiResponse> getAllYanki() {
        return yankiRepository.findAll().map(yanki -> YankiResponse.builder().amount(yanki.getAmount())
                .identityDocument(yanki.getIdentityDocument())
                .phoneNumber(yanki.getPhoneNumber())
                .build());
    }

    @Override
    public Flux<YankiResponse> getPhoneYanki(String phoneNumber) {
        return yankiRepository.findAll().filter(yanki -> yanki.getPhoneNumber().equals(phoneNumber))
                .map(yanki -> YankiResponse.builder().amount(yanki.getAmount())
                .identityDocument(yanki.getIdentityDocument())
                .phoneNumber(yanki.getPhoneNumber())
                .build());
    }

    @Override
    public Mono<Yanki> createYanki(Yanki yanki) {
        return yankiRepository.save(yanki);
    }


}