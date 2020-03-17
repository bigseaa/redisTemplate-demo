package com.bigsea.config;

import com.bigsea.Controller.ChatController;
import com.bigsea.subscribe.SubscribeOne;
import com.bigsea.subscribe.SubscribeTwo;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class subscribeConfig {
    private static final String RECEIVE_NAME = "receive";
    /**
     * 消息监听适配器一
     * @return MessageListenerAdapter
     */
    @Bean
    public MessageListenerAdapter listenerAdapterOne() {
        return new MessageListenerAdapter(new SubscribeOne(), RECEIVE_NAME);
    }

    /**
     * 消息监听适配器二
     * @return MessageListenerAdapter
     */
    @Bean
    public MessageListenerAdapter listenerAdapterTwo() {
        return new MessageListenerAdapter(new SubscribeTwo(), RECEIVE_NAME);
    }

    /**
     * 定义消息监听者容器
     * @param connectionFactory 连接工厂
     * @param listenerAdapterOne MessageListenerAdapter
     * @param listenerAdapterTwo MessageListenerAdapter
     * @return RedisMessageListenerContainer
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapterOne,
                                                   MessageListenerAdapter listenerAdapterTwo) {
        RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.addMessageListener(listenerAdapterOne, new PatternTopic(ChatController.CHAT_NAME));
        listenerContainer.addMessageListener(listenerAdapterTwo, new PatternTopic(ChatController.CHAT_NAME));
        return listenerContainer;
    }
}
