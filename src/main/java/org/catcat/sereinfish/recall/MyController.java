package org.catcat.sereinfish.recall;

import cc.sereinfish.catcat.core.controller.ann.Controller;
import cc.sereinfish.catcat.core.controller.ann.QMsg;
import com.IceCreamQAQ.Yu.annotation.Action;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.Stack;

@Controller
public class MyController {
    Logger logger;

    @Inject
    MsgStack msgStack;

    @Action("撤回")
    @QMsg(mastAtBot = true)
    public void recall(long group){
        Stack<MsgData> stack = msgStack.getMsgMap().getOrDefault(group, new Stack<>());
        //检查是否有需要弹出的消息
        while (!stack.isEmpty() && stack.peek().getTime() < System.currentTimeMillis() - msgStack.getMaxTime()){
            stack.pop();
        }
        if (stack.isEmpty()){
            logger.info("没有需要弹出的消息");
            return;
        }
        MsgData msgData = stack.pop();
        if (msgData != null && msgData.getSource() != null){
            msgData.getSource().recall();
        }
    }

    @Action("撤回所有")
    @QMsg(mastAtBot = true)
    public void recallAll(long group){
        Stack<MsgData> stack = msgStack.getMsgMap().getOrDefault(group, new Stack<MsgData>());

        //检查是否有需要弹出的消息
        while (stack.peek().getTime() < System.currentTimeMillis() - msgStack.getMaxTime()){
            stack.pop();
        }
        while (!stack.empty()){
            MsgData msgData = stack.pop();
            if (msgData != null && msgData.getSource() != null){
                msgData.getSource().recall();
            }
        }
    }
}