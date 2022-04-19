package org.catcat.sereinfish.recall;

import cc.sereinfish.catcat.core.controller.ann.Controller;
import cc.sereinfish.catcat.core.controller.ann.QMsg;
import com.IceCreamQAQ.Yu.annotation.Action;
import org.catcat.sereinfish.recall.config.ReCallConfig;

@Controller
public class MyControllerT {
    @Action("撤回 {key}")
    @QMsg(mastAtBot = true)
    public Object switc(boolean key, ReCallConfig config){
        config.setEnable(key);
        return "配置成功";
    }
}
