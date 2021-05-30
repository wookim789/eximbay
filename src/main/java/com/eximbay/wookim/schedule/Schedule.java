package com.eximbay.wookim.schedule;

import com.eximbay.wookim.document.SalesDoc;
import com.eximbay.wookim.repository.SalesRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.Properties;

@EnableScheduling
@RequiredArgsConstructor
@EnableMongoRepositories
@EnableAutoConfiguration
public class Schedule {
    private final SalesRepo repo;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        ObjectMapper mapper = new ObjectMapper();
        Properties config = new Properties();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("group.id", "sales_group");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(config);

        consumer.subscribe(Arrays.asList("sales"));

        while(true){
            ConsumerRecords<String, String> records = consumer.poll(500);
            for(ConsumerRecord<String, String> record: records){
                System.out.println(record.value());
                try{
                    SalesDoc doc = mapper.readValue(record.value(), SalesDoc.class);
                    System.out.println(doc.toString());
                    repo.save(doc);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
