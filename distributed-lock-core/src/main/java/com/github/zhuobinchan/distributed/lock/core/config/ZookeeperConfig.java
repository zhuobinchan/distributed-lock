package com.github.zhuobinchan.distributed.lock.core.config;

/**
 * @author zhuobin chan on 2020-12-29 17:20
 */
public class ZookeeperConfig {
    private int baseSleepTimeMs = 1000;
    private int maxRetries = 3;
    private String connectString;
    private String rootPath = "/ROOT/ROOT_LOCK/";


    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }

    public void setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
