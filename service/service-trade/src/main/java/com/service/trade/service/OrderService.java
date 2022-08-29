package com.service.trade.service;

import com.service.trade.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author alpha
 * @since 2022-08-05
 */
public interface OrderService extends IService<Order> {

    String createOrder(String courseId, String memberId);

}
