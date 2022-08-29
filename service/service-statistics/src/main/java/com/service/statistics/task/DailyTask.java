package com.service.statistics.task;

import com.service.statistics.service.DailyService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author alpha
 * @className: DailyTask
 * @date 2022/8/6 10:45
 * @Description
 */
@Component
public class DailyTask {
    @Autowired
    DailyService dailyService;
    @Scheduled(cron = "0 0 2 * * ?")
    public void genDaily(){
        String day = new DateTime().minusDays(1).toString("yyyy-MM-dd");
//        dailyService.
    }
}
