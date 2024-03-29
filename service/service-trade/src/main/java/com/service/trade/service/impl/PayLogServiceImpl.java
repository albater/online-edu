package com.service.trade.service.impl;

import com.service.trade.entity.PayLog;
import com.service.trade.mapper.PayLogMapper;
import com.service.trade.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author alpha
 * @since 2022-08-05
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
