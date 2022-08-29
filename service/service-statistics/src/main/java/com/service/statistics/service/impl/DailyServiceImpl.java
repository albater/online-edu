package com.service.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.service.base.exception.GlobalException;
import com.service.base.result.R;
import com.service.base.result.ResultCodeEnum;
import com.service.statistics.entity.Daily;
import com.service.statistics.feign.EduClient;
import com.service.statistics.feign.UcenterClient;
import com.service.statistics.mapper.DailyMapper;
import com.service.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author alpha
 * @since 2022-08-06
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {
    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void genDaily(String day) {
        //数据库中查到说明数据有误，这是由于我们这里是用来生成日期的，数据库中存在数据说明是冗余的错误数据，此时就抛出异常即可
        long count = this.count(new LambdaQueryWrapper<Daily>()
                .eq(Daily::getDateCalculated, day));
        if (count > 0) {
            throw new GlobalException(ResultCodeEnum.UNKNOWN_REASON);
        }
        R coursePublishNumR = eduClient.getCoursePublishNum(day);
        int coursePublishNum = Integer.parseInt(coursePublishNumR.getData().get("count").toString());
        if (coursePublishNum != 20000){
            throw new GlobalException(ResultCodeEnum.UNKNOWN_REASON);
        }
        int registerNum = Integer.parseInt(ucenterClient.getRegisterNum(day).toString());
        if (registerNum!=20000) {
            throw new GlobalException(ResultCodeEnum.UNKNOWN_REASON);
        }
        Daily daily = new Daily();
        daily.setCourseNum(coursePublishNum);
        daily.setRegisterNum(registerNum);
        daily.setDateCalculated(day);

        daily.setLoginNum(RandomUtils.nextInt(800,2000));
        daily.setVideoViewNum(RandomUtils.nextInt(2000,10000));
        this.save(daily);
    }

    @Override
    public Map<String, Object> getStatistics(String begin, String end) {
        List<Daily> dailys = this.list(new LambdaQueryWrapper<Daily>()
                .ge(Daily::getDateCalculated, begin)
                .le(Daily::getDateCalculated, end)
                .orderByAsc(Daily::getDateCalculated));
        List<String> date = dailys.stream().map(daily -> daily.getDateCalculated()).collect(Collectors.toList());
        List<Integer> registerNum = dailys.stream().map(daily -> daily.getRegisterNum()).collect(Collectors.toList());
        List<Integer> courseNum = dailys.stream().map(daily -> daily.getCourseNum()).collect(Collectors.toList());
        List<Integer> videoNum = dailys.stream().map(daily -> daily.getVideoViewNum()).collect(Collectors.toList());
        List<Integer> loginNum = dailys.stream().map(daily -> daily.getLoginNum()).collect(Collectors.toList());

        Map map = new HashMap<>();
        map.put("date",date);
        map.put("registerNum",registerNum);
        map.put("courseNum",courseNum);
        map.put("videoNum",videoNum);
        map.put("loginNum",loginNum);
        return map;
    }
}
