package com.xingtao.xingtaomall.order.web;

import com.alipay.api.AlipayApiException;


import com.xingtao.xingtaomall.order.config.AlipayTemplate;
import com.xingtao.xingtaomall.order.entity.OrderEntity;
import com.xingtao.xingtaomall.order.service.OrderService;
import com.xingtao.xingtaomall.order.vo.PayVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;



/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-07-08 10:54
 **/

@Slf4j
@Controller
public class PayWebController {

    @Autowired
    private AlipayTemplate alipayTemplate;

    @Autowired
    private OrderService orderService;

   // @Autowired
    //private BestPayService bestPayService;

    //@Resource
    //private WxPayConfig wxPayConfig;

    /**
     * 用户下单:支付宝支付
     * 1、让支付页让浏览器展示
     * 2、支付成功以后，跳转到用户的订单列表页
     * 3、GetMapping中，value、produces表示路径等信息更加具体
     * @param orderSn
     * @return
     * @throws AlipayApiException
     */
    @ResponseBody
    @GetMapping(value = "/aliPayOrder",produces = "text/html")
    public String aliPayOrder(@RequestParam("orderSn") String orderSn) throws AlipayApiException {

        PayVo payVo = orderService.getOrderPay(orderSn);
        //返回是一个页面，将此页面(pay)直接交给浏览器
        String pay = alipayTemplate.pay(payVo);
        System.out.println(pay);
        return pay;
    }


    /**
     * 微信支付
     * @param
     * @return
     */
   // @GetMapping(value = "/weixinPayOrder")
    //public String weixinPayOrder(@RequestParam("orderSn") String orderSn, Model model) {


    //}


    //根据订单号查询订单状态的API
    @GetMapping(value = "/queryByOrderId")
    @ResponseBody
    public OrderEntity queryByOrderId(@RequestParam("orderId") String orderId) {
        log.info("查询支付记录...");
        return orderService.getOrderByOrderSn(orderId);
    }



}
