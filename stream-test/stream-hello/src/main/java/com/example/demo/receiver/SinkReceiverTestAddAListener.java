package com.example.demo.receiver;

import com.example.demo.sender.SinkSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @Description:是不是监听到input之后，就不能transtform了？
 * @Author: wangkang
 * @Date: Created in 10:32 2018/7/17
 * @Modified By:
 */
@EnableBinding(value = {SinkSender.class})
public class SinkReceiverTestAddAListener {

    private static Logger logger = LoggerFactory.getLogger(SinkReceiverTestAddAListener.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload){
        logger.info("ReceivedTest: " + payload);
    }


}
