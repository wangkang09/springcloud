package com.example.demo.receiver;

import com.example.demo.sender.SinkOutput;
import com.example.demo.sender.SinkSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:32 2018/7/17
 * @Modified By:
 */
@EnableBinding(value = {SinkSender.class})//初始化SinkSender和SinkOutput bean.不能由下面那个代替
//@EnableBinding(value = {Processor.class})
public class SinkReceiverNative {

    private static Logger logger = LoggerFactory.getLogger(SinkReceiverNative.class);

//    @ServiceActivator(inputChannel = Sink.INPUT)
//    public void receive(Object payload){
//        logger.info("Received: " + payload);
//    }
//
//    @Transformer(inputChannel = Sink.INPUT,outputChannel = Sink.INPUT)
//    public String transfrom(Date date){
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//    }

//    @StreamListener(Source.OUTPUT)
//    public void red(Object ob){
//        logger.info("Received: " + ob);
//    }
}
