package com.service.trade.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.base.dto.CourseDto;
import com.service.base.dto.MemberDto;
import com.service.base.exception.GlobalException;
import com.service.base.result.R;
import com.service.base.result.ResultCodeEnum;
import com.service.common.util.utils.trade.OrderNoUtils;
import com.service.trade.entity.Order;
import com.service.trade.feign.EduClient;
import com.service.trade.feign.UcenterClient;
import com.service.trade.mapper.OrderMapper;
import com.service.trade.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author alpha
 * @since 2022-08-05
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    EduClient eduClient;
    @Autowired
    UcenterClient ucenterClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        //1.如果用户已购买并支付，返回异常
        Order order = this.getOne(new LambdaQueryWrapper<Order>()
                .eq(Order::getCourseId, courseId)
                .eq(Order::getMemberId, memberId));
        //2.判断order不为空或者数据库中的状态为1，说明已购买过
        if (order != null || order.getStatus() == 1) {
            throw new GlobalException(ResultCodeEnum.ORDER_EXIST_ERROR);
        }


        R courseDtoR = eduClient.getCourseDto(courseId);
        Object courseDtoRObj = courseDtoR.getData().get("item");
        R memberDtoR = ucenterClient.getMemberDto(memberId);
        Object memberDtoRobj = memberDtoR.getData().get("item");

        ObjectMapper objectMapper = new ObjectMapper();
        CourseDto courseDto = objectMapper.convertValue(courseDtoRObj, CourseDto.class);
        MemberDto memberDto = objectMapper.convertValue(memberDtoRobj, MemberDto.class);
        //3.用户已下单未支付，更新订单信息
        order.setStatus(0);
        order.setOrderNo(OrderNoUtils.getOrderNo());//获取订单编号
        //会员数据
        order.setMemberId(memberId);
        order.setNickname(memberDto.getNickname());
        order.setMobile(memberDto.getMobile());
        //课程详情
        order.setCourseTitle(courseDto.getTitle());
        order.setCourseCover(courseDto.getCover());
        //courseDto.getPrice()单元元，需要转为分
        long price = courseDto.getPrice().multiply(new BigDecimal("100")).longValue();
        order.setTotalFee(price);
        order.setTeacherName(courseDto.getTeacherName());
        order.setCourseId(courseId);
        //保存或者更新到数据库
        if (!StringUtils.isEmpty(order.getId())){
            this.updateById(order);
        }else {
            this.save(order);
        }
        //返回ID
        return order.getId();
    }
}
