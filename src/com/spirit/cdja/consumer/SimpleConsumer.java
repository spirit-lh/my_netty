package com.spirit.cdja.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumer {  
    public static void main(String[] args) throws Exception {  
          //Kafka consumer configuration settings  
          String topicName = "test";  
          Properties props = new Properties();  
   
          props.put("bootstrap.servers", "192.168.1.250:9092");  
          props.put("group.id", "test-consumer-group");  
          props.put("enable.auto.commit", "true");  
          props.put("auto.commit.interval.ms", "1000");  
          props.put("session.timeout.ms", "30000");  
          props.put("key.deserializer",  
             "org.apache.kafka.common.serialization.StringDeserializer");  
          props.put("value.deserializer",  
             "org.apache.kafka.common.serialization.StringDeserializer");  
          @SuppressWarnings("resource")  
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);  
   
          //Kafka Consumer subscribes list of topics here.  
          consumer.subscribe(Arrays.asList(topicName));  
   
          //print the topic name  
          System.out.println("Subscribed to topic "+ topicName);  
          while (true) {  
             ConsumerRecords<String, String> records = consumer.poll(1000);  
             for (ConsumerRecord<String, String> record : records) {
            	// print the offset,key and value for the consumer records.  
                 System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());  
             } 
          }  
   
       }  
}  