package com.xingtao.xingtaomall.order.listener;

import com.rabbitmq.client.Channel;
import com.xingtao.xingtaomall.order.entity.OrderEntity;
import com.xingtao.xingtaomall.order.service.OrderService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Description: 定时关闭订单（监听器）
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-07-07 09:54
 **/

@RabbitListener(queues = "order.release.order.queue")
@Service
public class OrderCloseListener {

    @Autowired
    private OrderService orderService;

    @RabbitHandler
    public void listener(OrderEntity orderEntity, Channel channel, Message message) throws IOException {
        System.out.println("收到过期的订单信息，准备关闭订单" + orderEntity.getOrderSn());
        try {
            orderService.closeOrder(orderEntity);
            //手动调用支付宝收单：提前将订单取消，使支付宝没法支付 p309 收单问题2

            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);  //拒绝消息，重新放回消息队列
        }

    }

}
