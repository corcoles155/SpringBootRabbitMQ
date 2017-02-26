package org.sanchez.corcoles.ana.pruebasconcepto.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    private static final String QUEUENAME = "micola";

    private static final String EXCHANGENAME = "miexchange";

    @Bean
    public ConnectionFactory buildConecctionFactory(){
        final CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost("localhost");
        cachingConnectionFactory.setPort(5672);
        cachingConnectionFactory.setUsername("admin");
        cachingConnectionFactory.setPassword("1234");
        cachingConnectionFactory.setVirtualHost("VHOST");
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate buildRabbitTemplate(ConnectionFactory connectionFactory){
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUENAME);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGENAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUENAME);
    }
}
