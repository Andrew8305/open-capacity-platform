package com.open.capacity.client.oauth2.service;

import com.open.capacity.client.oauth2.mapper.UserTimesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by Meng Ruo on 2018/6/7  14:11.
 **/
@Service
public class QuartzService {
    private static final Logger logger = LoggerFactory.getLogger(QuartzService.class);
    @Autowired
    private UserTimesMapper userTimesMapper;

    //    每分钟启动
    @Scheduled(cron = "0 0 1 * * ?")
    public void timerToNow() {
        logger.info("=========定时任务开始==========");
        userTimesMapper.cleanTimes();
    }
}
