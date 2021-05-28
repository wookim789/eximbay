package com.eximbay.wookim;

import com.eximbay.wookim.document.SalesDoc;
import com.eximbay.wookim.repository.SalesRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RequiredArgsConstructor
@SpringBootApplication
public class WookimApplication {

    @Autowired
    private static SalesRepo repo;
    public static void main(String[] args) throws Exception{
        SpringApplication.run(WookimApplication.class, args);

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
                    repo.save(doc);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
//        printJson(mapper);
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
