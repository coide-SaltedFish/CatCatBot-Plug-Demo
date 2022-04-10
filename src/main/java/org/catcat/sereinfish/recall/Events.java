package org.catcat.sereinfish.recall;

import cc.sereinfish.catcat.core.event.events.CatAppStartEvent;
import com.IceCreamQAQ.Yu.annotation.Event;
import com.IceCreamQAQ.Yu.annotation.EventListener;
import net.mamoe.mirai.event.events.GroupMessagePostSendEvent;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.Stack;

@EventListener
public class Events {
    Logger logger;

    @Inject
    MsgStack msgStack;

    @Event
    public void appStart(CatAppStartEvent event){
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
        stack.push(new MsgData(System.currentTimeMillis(), event.getReceipt()));
    }
}
