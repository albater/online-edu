package com.service.statistics.controller.admin;


import com.service.base.result.R;
import com.service.statistics.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author alpha
 * @since 2022-08-06
 */
@RestController
@RequestMapping("/admin/statistics/daily")
public class AdminDailyController {
    @Autowired
    DailyService dailyService;
    //生成指定日期的统计数据
    @PostMapping("/genDaily/{day}")
    public R genDaily(@PathVariable("day")String day) {
        dailyService.genDaily(day);
        return R.ok();
    }
//@Scheduled
    //查询日期区间内的统计数据
    @GetMapping(value = "getStatistics/{begin}/{end}")
    public R getStatistics(@PathVariable("begin") String begin,
                           @PathVariable("end") String end){
        Map<String,Object> map = dailyService.getStatistics(begin,end);
        return R.ok().data("map",map);
    }
}

