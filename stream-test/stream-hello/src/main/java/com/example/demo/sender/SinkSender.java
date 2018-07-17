package com.example.demo.sender;

import com.example.demo.receiver.SinkReceiver;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 11:05 2018/7/17
 * @Modified By:
 */
public interface SinkSender {

    @Input(Sink.INPUT)
    SubscribableChannel input();
}
