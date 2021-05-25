package com.example.kafka.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerFastStart {

    private static final String BOOTSTRAP_SERVERS = "192.168.1.105:9092";

    private static final String TOPIC = "test";

    public static void main(String [] args) {

        Properties properties = new Properties();
        //设置重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG,10);
        //设置key序列化器
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        //设置值序列化器
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        //设置集群地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC,"kafka-demo","hello,kafka");
        try {
            producer.send(producerRecord);
        }catch (Exception e){
            e.printStackTrace();
        }
        producer.close();

    }


}
