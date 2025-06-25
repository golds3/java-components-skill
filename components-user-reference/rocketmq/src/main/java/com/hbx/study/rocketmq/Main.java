package com.hbx.study.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException, IOException {
        // 1. 创建一个生产者组
        DefaultMQProducer producer = new DefaultMQProducer("producer_group");

        Socket socket = new Socket("127.0.0.1", 9876);
        System.out.println("Connected!");


        // 设置 NameServer 地址（强烈建议写 localhost）
        producer.setNamesrvAddr("localhost:9876");

        // 设置发送超时时间，默认是 3000ms，可能不够
        producer.setSendMsgTimeout(10000);


        // 建议将 producer.start() 放入 try-with-resources 或 try-catch 块中确保关闭
        try {
            producer.start();
            Message msg = new Message("TopicTest", "TagA", "Hello RocketMQ!".getBytes());
            SendResult result = producer.send(msg);
            System.out.println("Send result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }

    }
}
