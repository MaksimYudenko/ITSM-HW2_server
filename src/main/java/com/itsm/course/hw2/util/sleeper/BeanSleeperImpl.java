package com.itsm.course.hw2.util.sleeper;

import org.springframework.stereotype.Component;

@Component
public class BeanSleeperImpl implements BeanSleeper {

    @Override
    public void sleep(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}