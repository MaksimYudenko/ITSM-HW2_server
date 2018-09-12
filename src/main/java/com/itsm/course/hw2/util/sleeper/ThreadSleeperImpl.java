package com.itsm.course.hw2.util.sleeper;

import org.springframework.stereotype.Component;

@Component
public class ThreadSleeperImpl implements ThreadSleeper {

    @Override
    public void sleep(long delay) {
        try {
            System.out.println("wait...");
            long start = System.currentTimeMillis();
            Thread.sleep(delay);
            long end = System.currentTimeMillis();
            System.out.println("You waited for " + (end - start) + " seconds.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}