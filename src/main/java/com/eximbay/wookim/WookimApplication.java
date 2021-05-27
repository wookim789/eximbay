package com.eximbay.wookim;

import com.eximbay.wookim.document.SalesDoc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Properties;

@SpringBootApplication
public class WookimApplication {

    public static void main(String[] args) {
        SpringApplication.run(WookimApplication.class, args);

        Properties config = new Properties();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("group.id", "sales_group");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(config);

        consumer.subscribe(Arrays.asList("sales"));

        ObjectMapper mapper = new ObjectMapper();
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(500);
            for(ConsumerRecord<String, String> record: records){
//                System.out.println(record.value());
                try{
                    SalesDoc doc = mapper.readValue(record.value(), SalesDoc.class);
                }catch (Exception e){

                }
            }
        }
    }

    private void printJson(){
//        {
//            "id": 1,
//                "title": "title1",
//                "interfaceType": "api",
//                "price": "1000",
//                "regDate": "",
//                "modDate": ""
//        }
        Integer id = 1;
        String title = "title" + id;
        String interfaceType = "api";

    }
}
