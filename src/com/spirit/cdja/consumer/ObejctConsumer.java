package com.spirit.cdja.consumer;
import java.util.Arrays;  
import java.util.Properties;  
  
import org.apache.kafka.clients.consumer.ConsumerRecord;  
import org.apache.kafka.clients.consumer.ConsumerRecords;  
import org.apache.kafka.clients.consumer.KafkaConsumer; 
public class ObejctConsumer {
	public static void main(String[] args) throws Exception {  
		  
        String topicName = "test";  
        Properties props = new Properties();  
          
        props.put("bootstrap.servers", "192.168.1.250:9092");  
        props.put("group.id", "test-consumer-group");  
        props.put("enable.auto.commit", "true");   
        props.put("auto.commit.interval.ms", "1000");  
        props.put("session.timeout.ms", "30000");  
          
        //要发送自定义对象，需要指定对象的反序列化类  
       props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");  
        props.put("value.deserializer", "com.spirit.cdja.util.DecodeingKafka");
          
        //使用String时可以使用系统的反序列化类  
//      props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");  
//      props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");  
        @SuppressWarnings("resource")  
      KafkaConsumer<String, Object> consumer = new KafkaConsumer<String, Object>(props);  
        //Kafka Consumer subscribes list of topics here.  
        consumer.subscribe(Arrays.asList(topicName));  
        //print the topic name  
        System.out.println("Subscribed to topic "+ topicName);  
          
          
        while (true) {  
            ConsumerRecords<String, Object> records = consumer.poll(100);  
           for (ConsumerRecord<String, Object> record : records)  
           // print the offset,key and value for the consumer records.  
//         System.out.printf("offset = %d, key = %s, value = %s\n",   
//            record.offset(), record.key(), record.value().toString());  
                 
               System.out.println(record.toString());  
        }  
          
     }  
}  