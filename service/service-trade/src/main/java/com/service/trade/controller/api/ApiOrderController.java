package com.service.trade.controller.api;


import com.service.base.jwt.JwtHelper;
import com.service.base.jwt.JwtInfo;
import com.service.base.result.R;
import com.service.trade.service.OrderService;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author alpha
 * @since 2022-08-05
 */
@RestController
@RequestMapping("/trade/order")
public class ApiOrderController {

    @Resource
    private OrderService orderService;
    //1. 创建订单返回订单id
    @PostMapping("auth/createOrder/{courseId}")
    public R createOrder(@PathVariable("courseId") String courseId,  HttpServletRequest request){
        String memberId = JwtHelper.getId(request);

        String orderId = orderService.createOrder(courseId,memberId);

        return R.ok().data("orderId",orderId );
    }
}

