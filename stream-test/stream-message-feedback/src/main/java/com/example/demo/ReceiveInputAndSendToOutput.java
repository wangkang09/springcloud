package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * @Description：监听input主题，并处理此消息，再转发到output主题中去,两种方法都实现了转发，transfrom注解应该也可以呀
 * @Author: wangkang
 * @Date: Created in 15:25 2018/7/17
 * @Modified By:
 */
@EnableBinding(value = Processor.class)
public class ReceiveInputAndSendToOutput {

    private static Logger logger = LoggerFactory.getLogger(ReceiveInputAndSendToOutput.class);

//    @StreamListener(Processor.OUTPUT)
//    @SendTo(Processor.INPUT)
//    public Object receiveFromInput(Object payload){
//        logger.info("Received: " + payload);
//        return "From Input Channel Return - " + payload;
//    }

    @ServiceActivator(inputChannel = Processor.OUTPUT,outputChannel = Processor.INPUT)
    public Object receiveFromInput(Object payload){
        logger.info("Received: " + payload);
        return "From Channel Return - " + payload;
    }
}
