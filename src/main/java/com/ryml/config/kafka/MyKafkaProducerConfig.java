package com.ryml.config.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/8/20 23:30
 */
@Configuration
public class MyKafkaProducerConfig {


    @Bean
    public KafkaProducer testKafkaProducer(){
        Properties props=new Properties();
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,true);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.190.128:9092,192.168.190.128:9093,192.168.190.128:9094");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<String, String> kafkaProducer=new KafkaProducer<String, String>(props);
        return kafkaProducer;
    }

}
