package org.catcat.sereinfish.recall.job;

import com.IceCreamQAQ.Yu.annotation.Cron;
import org.catcat.sereinfish.recall.MsgStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class MyJob {
    private Logger logger = LoggerFactory.getLogger(MyJob.class);

    @Inject
    MsgStack msgStack;

    @Cron("30s")
    public void job1(){
        logger.info("消息栈大小：" + msgStack.getMsgMap().size());
    }

    @Cron("At::h::00")
    public void job2(){
        logger.info("新的一小时");
    }
}
