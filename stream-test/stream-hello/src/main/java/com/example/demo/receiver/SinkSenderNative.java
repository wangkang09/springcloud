package com.example.demo.receiver;

import com.example.demo.sender.SinkOutput;
import com.example.demo.sender.SinkSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.util.Date;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:32 2018/7/17
 * @Modified By:
 */
@EnableBinding(value = {SinkSender.class})//初始化SinkSender和SinkOutput bean.不能由下面那个代替
//@EnableBinding(value = {Processor.class})
public class SinkSenderNative {

    private static Logger logger = LoggerFactory.getLogger(SinkSenderNative.class);

//    @Bean
//    @InboundChannelAdapter(value = SinkSenderNative.SinkOutput.OUTPUT,poller = @Poller(fixedDelay = "2000"))
//    public MessageSource<Date> timerMessageSource(){
//        return () -> new GenericMessage<>(new Date());
//    }
//
//    public interface SinkOutput {
//        String OUTPUT = "input";
//
//        @Output(SinkOutput.OUTPUT)
//        MessageChannel output();
//    }
}
