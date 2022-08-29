package com.service.statistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.service.statistics.entity.Daily;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author alpha
 * @since 2022-08-06
 */
public interface DailyService extends IService<Daily> {

    void genDaily(String day);


    Map<String, Object> getStatistics(String begin, String end);
}
