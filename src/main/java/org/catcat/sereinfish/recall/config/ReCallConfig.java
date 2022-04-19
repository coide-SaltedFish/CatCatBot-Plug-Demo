package org.catcat.sereinfish.recall.config;

import cc.sereinfish.catcat.core.plug.config.CatConfig;
import cc.sereinfish.catcat.core.plug.config.ConfigType;

@CatConfig(type = ConfigType.GROUP)
public class ReCallConfig {
    boolean enable = false;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
