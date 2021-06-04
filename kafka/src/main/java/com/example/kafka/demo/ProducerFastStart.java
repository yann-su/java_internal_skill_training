package com.example.kafka.demo;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class ProducerFastStart {

    private static final String BOOTSTRAP_SERVERS = "192.168.1.105:9092";

    private static final String TOPIC = "test";

    public static void main(String [] args) {

        Properties properties = new Properties();
        //设置重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG,10);
        //设置key序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //设置值序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        //设置集群地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC,"23131131","hello,kafka");
        try {
            //同步发送
            Future<RecordMetadata> send = producer.send(producerRecord);
            RecordMetadata recordMetadata = send.get();
            System.out.println(recordMetadata.topic());
            System.out.println(recordMetadata.timestamp());
            System.out.println(recordMetadata.partition());
            System.out.println(recordMetadata.offset());

            producer.send(producerRecord, (metadata, exception) -> {
                if (exception == null){
                    System.out.println(metadata.topic());
                    System.out.println(metadata.timestamp());
                    System.out.println(metadata.partition());
                    System.out.println(metadata.offset());
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
        producer.close();

    }


}
