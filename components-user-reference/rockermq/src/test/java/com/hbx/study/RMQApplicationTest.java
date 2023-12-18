package com.hbx.study;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.io.UnsupportedEncodingException;

@SpringBootTest
@Slf4j
public class RMQApplicationTest {


    @Resource
    private RocketMQTemplate rocketmqTemplate;

    @Test
    public void test1() throws UnsupportedEncodingException {
        Message<String> msg = MessageBuilder.withPayload("Hello,RocketMQ-SpringBoot")
                //设置消息的key
                .setHeader(RocketMQHeaders.KEYS,"000002")
                .build();
        SendResult sendResult = rocketmqTemplate.syncSend("TopicTest:TAG1", msg);
        log.debug("========消息发送结果=========\n"+sendResult);
    }

    public static void main(String[] args) throws Exception {
//        // 初始化一个producer并设置Producer group name
//        DefaultMQProducer producer = new DefaultMQProducer("test_mq_producers"); //（1）
//        // 设置NameServer地址
//        producer.setNamesrvAddr("192.168.127.28:9876");  //（2）
//        // 启动producer
//        producer.start();
//        for (int i = 0; i < 100; i++) {
//            // 创建一条消息，并指定topic、tag、body等信息，tag可以理解成标签，对消息进行再归类，RocketMQ可以在消费端对tag进行过滤
//            Message msg = new Message("TopicTest" /* Topic */,
//                    "TagA" /* Tag */,
//                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
//            );   //（3）
//            // 利用producer进行发送，并同步等待发送结果
//            SendResult sendResult = producer.send(msg);   //（4）
//            System.out.printf("%s%n", sendResult);
//        }
//        // 一旦producer不再使用，关闭producer
//        producer.shutdown();
    }
}
