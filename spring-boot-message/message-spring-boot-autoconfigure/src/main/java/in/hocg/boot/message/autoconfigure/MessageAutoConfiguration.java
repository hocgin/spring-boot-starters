package in.hocg.boot.message.autoconfigure;

import in.hocg.boot.message.core.TransactionalMessageListener;
import in.hocg.boot.message.core.TransactionalMessageService;
import in.hocg.boot.message.data.TransactionalAspect;
import in.hocg.boot.message.data.client.JdbcTransactionalMessageServiceImpl;
import in.hocg.boot.message.service.local.LocalMessageQueueService;
import in.hocg.boot.message.service.local.LocalMessageService;
import in.hocg.boot.message.service.normal.NoneMessageQueueService;
import in.hocg.boot.message.service.normal.NormalMessageService;
import in.hocg.boot.message.service.normal.rocket.RocketMessageQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import javax.sql.DataSource;

/**
 * Created by hocgin on 2019/6/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@ConditionalOnBean({DataSource.class})
@ConditionalOnClass({Aspect.class})
@ConditionalOnProperty(prefix = MessageProperties.PREFIX, name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(MessageProperties.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MessageAutoConfiguration {
    private final MessageProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public TransactionalMessageService transactionalMessageService(DataSource dataSource) {
        return new JdbcTransactionalMessageServiceImpl(dataSource);
    }

    @Bean
    @ConditionalOnMissingBean
    public TransactionalMessageListener transactionalMessageListener() {
        return new TransactionalMessageListener();
    }

    @Bean
    @ConditionalOnMissingBean
    public TransactionalAspect transactionalAspect(ApplicationEventPublisher publisher) {
        return new TransactionalAspect(publisher);
    }

    @Bean
    @ConditionalOnMissingBean
    public LocalMessageService localMessageService() {
        return new LocalMessageQueueService();
    }

    @Bean
    @ConditionalOnMissingBean
    public NormalMessageService normalMessageService() {
        MessageProperties.MessageType messageType = properties.getType();
        if (MessageProperties.MessageType.Rocket.equals(messageType)) {
            return new RocketMessageQueueService();
        }
        return new NoneMessageQueueService();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(RedisMessageListenerContainer.class)
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        return container;
    }
}
