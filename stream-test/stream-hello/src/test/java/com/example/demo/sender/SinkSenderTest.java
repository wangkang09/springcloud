package com.example.demo.sender;

import com.example.demo.receiver.SinkReceiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 11:26 2018/7/17
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SinkSenderTest {

    @Autowired
    private SinkSender sinkSender;



    @Autowired
    private MessageChannel input;

    @Test
    public void output() throws Exception {

        sinkSender.input().send(MessageBuilder.withPayload("From SinkSender").build());
        //input.send(MessageBuilder.withPayload("From SinkSender").build());
    }

}