package com.nttdata.bootcamp.controller;

import com.nttdata.bootcamp.dto.DepositResponse;
import com.nttdata.bootcamp.dto.YankiResponse;
import com.nttdata.bootcamp.model.Yanki;
import com.nttdata.bootcamp.model.Deposit;
import com.nttdata.bootcamp.producerKafka.KafkaProducer;
import com.nttdata.bootcamp.service.DepositService;
import com.nttdata.bootcamp.service.YankiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/yanki")
@Slf4j
public class YankiController {

    @Autowired
    YankiService yankiService;

    @Autowired
    DepositService depositService;

    @Autowired
    private KafkaProducer kafkaProducer;

    //Payment Yanki

    @GetMapping(value = "/allYanki")
    @ResponseStatus(HttpStatus.OK)
    public Flux<YankiResponse> getAllYanki(){
        log.info("All Yanki Accounts.");
        return yankiService.getAllYanki();
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Yanki> createYanki(@RequestBody Yanki yanki){
        log.info("Yanki Account Creation Success.");
        return yankiService.createYanki(yanki);
    }

    @GetMapping(value = "/{phoneNumber}")
    public Flux<YankiResponse> getPhoneYanki(@PathVariable String phoneNumber){
        log.info("Yanki by PhoneNumber");
        return yankiService.getPhoneYanki(phoneNumber);
    }

    //Deposit Yanki

    @GetMapping(value = "/deposit/allYankiDeposit")
    @ResponseStatus(HttpStatus.OK)
    public Flux<DepositResponse> getAllYankiDeposit(){
        log.info("List All Deposits.");
        return depositService.getAllYanki();
    }

    @PostMapping(value = "/deposit/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Deposit> createYankiDeposit(@RequestBody Deposit deposit){
        log.info("Deposit Success.");
        return depositService.createYanki(deposit);
    }

    @GetMapping(value = "/deposit/{phoneNumber}")
    public Flux<DepositResponse> getPhoneYankiDeposit(@PathVariable String phoneNumber){
        log.info("Deposits by PhoneNumber");
        return depositService.getPhoneYanki(phoneNumber);
    }

    @PostMapping(value = "/account/yanki")
    public ResponseEntity<String> requestYanki (@RequestBody String yanki){
        kafkaProducer.publishMessage(yanki);
        return ResponseEntity.ok(yanki);
    }

    @PostMapping(value = "/account/deposit/yanki")
    public ResponseEntity<String> requestYankiDeposit (@RequestBody String yanki){
        kafkaProducer.publishMessage(yanki);
        return ResponseEntity.ok(yanki);
    }

}
