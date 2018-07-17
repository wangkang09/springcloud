package com.example.demo.receiver;

import com.example.demo.sender.SinkOutput;
import com.example.demo.sender.SinkSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;

/**
 * @Description:当一个通道被监听了，就不能进行转发了！
 * @Author: wangkang
 * @Date: Created in 10:32 2018/7/17
 * @Modified By:
 */
@EnableBinding(value = {SinkSender.class, SinkOutput.class})//初始化SinkSender和SinkOutput bean.不能由下面那个代替
//@EnableBinding(value = {Processor.class})
public class SinkReceiver {

    private static Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

//    @StreamListener(Sink.INPUT)
//    public void receive(Object payload){
//        logger.info("Received: " + payload);
//    }

//    @Transformer(inputChannel = Sink.INPUT,outputChannel = Source.OUTPUT)
//    public String transfrom(Object in){
//        return in + "wo shi ni de";
//    }

    @ServiceActivator(inputChannel = Sink.INPUT,outputChannel = Source.OUTPUT)
    public String transfrom(Object in){
        return in + "wo shi ni de";
    }

    @StreamListener(Source.OUTPUT)
    public void red(Object ob){
        logger.info("ReceivedOut: " + ob);
    }
}
