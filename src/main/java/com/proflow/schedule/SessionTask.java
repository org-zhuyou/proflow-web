package com.proflow.schedule;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.mapper.Condition;
import com.proflow.service.LocalSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Leonid on 2019/4/28.
 */
@Component
@Configuration
@EnableScheduling
public class SessionTask {

    private static Logger logger = LoggerFactory.getLogger(SessionTask.class);

    @Autowired
    private LocalSessionService localSessionService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    private void configureTasks() {
        Date now = new Date();
        logger.info("清理过期session数据 {}", DateUtil.format(now, "yyyy-MM-dd HH:mm:ss"));
        // 清理session
        localSessionService.delete(Condition.create().lt("expiry_time", now));
    }
}
