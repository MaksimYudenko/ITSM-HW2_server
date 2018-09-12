package com.itsm.course.hw2;

import com.itsm.course.hw2.core.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerMain {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Server server = context.getBean(Server.class);
        context.registerShutdownHook();

        while (true) server.run();
    }

}