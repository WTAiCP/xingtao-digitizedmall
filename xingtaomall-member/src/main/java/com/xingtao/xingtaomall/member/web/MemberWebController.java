package com.xingtao.xingtaomall.member.web;

import com.alibaba.fastjson.JSON;
import com.xingtao.common.utils.R;
import com.xingtao.xingtaomall.member.feign.OrderFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: 兴涛
 * @createTime: 2022-07-08 13:39
 **/

@Controller
public class MemberWebController {

    @Autowired
    private OrderFeignService orderFeignService;

    @PostMapping(value = "/memberOrder.html")
    public String memberOrderPage(@RequestParam(value = "pageNum",required = false,defaultValue = "0") Integer pageNum,
                                  Model model, HttpServletRequest request) {

        //获取到支付宝给我们转来的所有请求数据,支付宝会传非常多的数据在URL中
        //request,验证签名，如果正确，可以去修改订单状态


        //查出当前登录用户的所有订单列表数据
        Map<String,Object> page = new HashMap<>();  //分页数据
        page.put("page",pageNum.toString());

        //远程查询订单服务订单数据
        R orderInfo = orderFeignService.listWithItem(page);
        System.out.println(JSON.toJSONString(orderInfo));
        model.addAttribute("orders",orderInfo);

        return "orderList";
    }

}
