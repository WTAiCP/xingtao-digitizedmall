package com.xingtao.xingtaomall.ware.listener;

import com.rabbitmq.client.Channel;
import com.xingtao.common.to.OrderTo;
import com.xingtao.common.to.mq.StockLockedTo;
import com.xingtao.xingtaomall.ware.service.WareSkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-07-07 00:20
 **/

@Slf4j
@RabbitListener(queues = "stock.release.stock.queue")
@Service
public class StockReleaseListener {

    @Autowired
    private WareSkuService wareSkuService;

    /**
     * 1、库存自动解锁
     *  下订单成功，库存锁定成功，接下来的业务调用失败，导致订单回滚。之前锁定的库存就要自动解锁
     *
     *  2、订单失败
     *      库存锁定失败
     *
     *   只要解锁库存的消息失败，一定要告诉服务解锁失败
     */
    @RabbitHandler
    public void handleStockLockedRelease(StockLockedTo to, Message message, Channel channel) throws IOException {
        log.info("******收到解锁库存的信息******");
        try {

            //当前消息是否被第二次及以后（重新）派发过来了 防止消息重复，但是有些暴力，可能是第一次失败的情况重新发送的消息
            // Boolean redelivered = message.getMessageProperties().getRedelivered();

            //解锁库存
            wareSkuService.unlockStock(to);
            // 手动删除消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            // 解锁失败 将消息重新放回队列，让别人消费
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }
    }

    //p298
    /**
     * 订单解锁后会向stock.release.stock.queue发送消息
     * 防止订单服务卡顿
     * @param orderTo
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitHandler
    public void handleOrderCloseRelease(OrderTo orderTo, Message message, Channel channel) throws IOException {

        log.info("******收到订单关闭，准备解锁库存的信息******");

        try {
            wareSkuService.unlockStock(orderTo);
            // 手动删除消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (Exception e) {
            // 解锁失败 将消息重新放回队列，让别人消费
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
        }
    }


}
