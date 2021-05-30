package com.eximbay.wookim;

import com.eximbay.wookim.document.SalesDoc;
import com.eximbay.wookim.repository.SalesRepo;
import com.eximbay.wookim.service.SalesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class WookimApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(WookimApplication.class, args);
    }

    private static void printJson(ObjectMapper mapper) throws Exception{
        for(int id = 0; id < 1000; id++){
            String title = "title" + id;
            String interfaceType = "api";
            String price = Integer.toString(1000 + id * 5);
            String regDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);

            Map<String, Object> map = new HashMap<>();

            map.put("id", id);
            map.put("title", title);
            map.put("interfaceType", interfaceType);
            map.put("price", price);
            map.put("regDate", regDate);
            System.out.println(mapper.writeValueAsString(map));
        }

    }
}
