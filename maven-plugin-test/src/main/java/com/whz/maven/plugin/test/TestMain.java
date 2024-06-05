package com.whz.maven.plugin.test;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 测试配置类
 * </p>
 *
 * @author: laiqi
 * @since: 2021-12-02
 */
public class TestMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("测试启动成功...");
        for (int i = 0; i < 5; i++) {
            System.out.println(Calendar.getInstance().getTime() + ":" + i);
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
