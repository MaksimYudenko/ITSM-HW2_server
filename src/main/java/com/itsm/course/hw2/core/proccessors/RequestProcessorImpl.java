package com.itsm.course.hw2.core.proccessors;

import com.itsm.course.hw2.dto.Request;
import com.itsm.course.hw2.util.sleeper.BeanSleeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class RequestProcessorImpl implements RequestProcessor {

    @Value("${delay}")
    private Integer delay;
    private final BeanSleeper sleeper;

    @Autowired
    public RequestProcessorImpl(BeanSleeper sleeper) {
        this.sleeper = sleeper;
    }

    @Override
    public String process(Request request) {
        String message = request.getMessage();
        String name = request.getName();
        sleeper.sleep(delay);
        System.out.println(String.format("message from: %s, content: %s", name, message));
        return "Hello, " + name;
    }

}