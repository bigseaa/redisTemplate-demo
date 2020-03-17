package com.bigsea.subscribe;

/**
 * 消费者二
 */
public class SubscribeTwo {
    public void receive(String message) {
        System.out.println("这里是二号订阅客户端，接收到信息：" + message);
    }
}
