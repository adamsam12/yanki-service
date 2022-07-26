package com.nttdata.bootcamp.service.impl;

import com.nttdata.bootcamp.dto.YankiResponse;
import com.nttdata.bootcamp.model.Yanki;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface YankiServiceImpl {
    Flux<YankiResponse> getPhoneYanki(String phoneNumber);
    Mono<Yanki> createYanki(Yanki yanki);
    Flux<YankiResponse> getAllYanki();
}
