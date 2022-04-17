package org.catcat.sereinfish.recall;

import cc.sereinfish.catcat.core.database.service.MessageService;
import cc.sereinfish.catcat.core.event.events.CatAppStartEvent;
import com.IceCreamQAQ.Yu.annotation.Event;
import com.IceCreamQAQ.Yu.annotation.EventListener;
import net.mamoe.mirai.event.events.GroupMessagePostSendEvent;
import org.catcat.sereinfish.recall.db.entity.TestEntity;
import org.catcat.sereinfish.recall.db.service.TestService;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.Stack;

@EventListener
public class Events {
    Logger logger;

    @Inject
    MsgStack msgStack;

    @Inject
    MessageService messageService;

    @Inject
    TestService testService;

    @Event
    public void appStart(CatAppStartEvent event){
        TestEntity entity = new TestEntity();
        testService.save(entity);

        logger.info("插件启动");
    }

    @Event
    public void onMessage(GroupMessagePostSendEvent event){
        if (!msgStack.getMsgMap().containsKey(event.getTarget().getId())){
            msgStack.getMsgMap().put(event.getTarget().getId(), new Stack<>());
        }

        Stack<MsgData> stack = msgStack.getMsgMap().getOrDefault(event.getTarget().getId(), new Stack<>());
        msgStack.getMsgMap().put(event.getTarget().getId(), stack);

        //检查是否有需要弹出的消息
        while (!stack.isEmpty() && stack.peek().getTime() < System.currentTimeMillis() - msgStack.getMaxTime()){
            stack.pop();
        }
        logger.info("消息已记录");
        stack.push(new MsgData(System.currentTimeMillis(), event.getReceipt()));
    }
}
