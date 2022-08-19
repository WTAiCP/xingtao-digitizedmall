package com.xingtao.xingtaomall.order.config;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.order.coonfig
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/6/8
 */
@Configuration
public class MyRabbitConfig {

    //@Autowired  循环依赖。当类只有一个有参构造器时，参数会从容器中得到
   /* RabbitTemplate rabbitTemplate;
    public MyRabbitConfig(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        initRabbitTemplate();
    }*/

    RabbitTemplate rabbitTemplate;

    @Primary
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setMessageConverter(messageConverter());
        initRabbitTemplate();
        return rabbitTemplate;
    }

    /**
     * 消息转换
     * 将对象信息转为json放入队列
     * @return
     */
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 定制RabbitTemplate
     * 1、服务收到消息就会回调
     *      1、spring.rabbitmq.publisher-confirms: true
     *      2、设置确认回调
     * 2、消息正确抵达队列就会进行回调
     *      1、spring.rabbitmq.publisher-returns: true
     *         spring.rabbitmq.template.mandatory: true
     *      2、设置确认回调ReturnCallback
     *
     * 3、消费端确认(保证每个消息都被正确消费，此时才可以broker删除这个消息)
     *      1、默认是自动确认。如果消息没有完全被消费就突然宕机，那么消息就会丢失。由于是自动确认的原因
     *      2、所以需要手动确认，收到一个确认一个，不确认的不会被删除
     *
     */
    // @PostConstruct  //MyRabbitConfig对象创建完成以后，执行这个方法
    public void initRabbitTemplate() {

        /**
         * 1、只要消息抵达Broker就ack=true,无论其是否被消费
         * correlationData：当前消息的唯一关联数据(这个是消息的唯一id)
         * ack：消息是否成功收到
         * cause：失败的原因
         *
         * todo 1、需要手动ack：当消费者消费消息成功后回复一个ack
         * todo 2、每一个发送的消息再数据库做好记录，失败后再重新发送
         */
        //设置确认回调
        rabbitTemplate.setConfirmCallback((correlationData,ack,cause) -> {
            System.out.println("confirm...correlationData["+correlationData+"]==>ack:["+ack+"]==>cause:["+cause+"]");
        });


        /**
         * 只要消息没有投递给指定的队列，就触发这个失败回调
         * 投递成功的话，不会触发回调
         * message：投递失败的消息详细信息
         * replyCode：回复的状态码
         * replyText：回复的文本内容
         * exchange：当时这个消息发给哪个交换机
         * routingKey：当时这个消息用哪个路邮键
         */
        rabbitTemplate.setReturnCallback((message,replyCode,replyText,exchange,routingKey) -> {
            System.out.println("Fail Message["+message+"]==>replyCode["+replyCode+"]" +
                    "==>replyText["+replyText+"]==>exchange["+exchange+"]==>routingKey["+routingKey+"]");
        });
    }
}
