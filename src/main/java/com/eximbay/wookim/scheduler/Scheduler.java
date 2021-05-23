package com.eximbay.wookim.scheduler;

import com.eximbay.wookim.document.SalesDoc;
import com.eximbay.wookim.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Scheduler {
    private final SalesService salesService;

    @Scheduled(fixedDelay = 500)
    public void jobSch(){
        String date = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
        System.out.println(date);

        List<SalesDoc> sales = new ArrayList<>();
        try{
            salesService.insertSales(sales);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
