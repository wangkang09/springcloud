package com.example.demo.sender;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 14:17 2018/7/17
 * @Modified By:
 */
public interface SinkOutput {

    @Output(Source.OUTPUT)
    MessageChannel output();
}
