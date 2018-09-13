package by.itsm.course.hw2s.util.sleeper;

import org.springframework.stereotype.Component;

@Component
public class ThreadSleeperImpl implements ThreadSleeper {

    @Override
    public void sleep(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}