package com.eximbay.wookim.controller;

import com.eximbay.wookim.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KafkaController {
    private final KafkaService kafkaService;

    @PostMapping("/sales")
    public void postSales(){
        kafkaService.reportCurrentTime();
    }
}
