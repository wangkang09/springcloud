package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import java.util.Date;

/**
 * @Description:input通道的生产者和output通道的消费者
 * @Author: wangkang
 * @Date: Created in 15:32 2018/7/17
 * @Modified By:
 */
@EnableBinding(value = Processor.class)
public class InputProducerAndOutputConsumer {

    private static Logger logger = LoggerFactory.getLogger(InputProducerAndOutputConsumer.class);

    @Bean
    @InboundChannelAdapter(value = Processor.OUTPUT, poller = @Poller(fixedDelay = "2000"))
    public MessageSource<Date> timerMessageSource(){
        return () -> new GenericMessage<>(new Date());
    }

    @StreamListener(Processor.INPUT)
    public void receiceFromOutput(Object payload){
        logger.info("Received: " + payload);
    }

}
