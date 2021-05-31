package com.example.kafka.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

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

//        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"producer-1"); // 每台机器唯一

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(TOPIC,"23131131","hello,kafka,Transactions");
        //初始化事物
        producer.initTransactions();
        try {
            //开启一个事物
            producer.beginTransaction();
            producer.send(producerRecord);
            //提交
            producer.commitTransaction();
        }catch (Exception e){
            // 5.放弃事务
            e.printStackTrace();
            producer.abortTransaction();
        }
        producer.close();

    }


}
