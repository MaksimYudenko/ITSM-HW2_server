package com.itsm.course.hw2;

import com.itsm.course.hw2.core.ServerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        ServerService serverService = context.getBean(ServerService.class);
        context.registerShutdownHook();

        while (true) serverService.run();
    }

}