package com.bigsea.subscribe;

/**
 * 消费者一
 */
public class SubscribeOne {
    public void receive(String message) {
        System.out.println("这里是一号订阅客户端，接收到信息：" + message);
    }
}
