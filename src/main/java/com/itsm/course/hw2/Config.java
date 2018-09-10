package com.itsm.course.hw2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsm.course.hw2.core.ServerService;
import javax.inject.Provider;
import com.itsm.course.hw2.core.proccessors.RequestProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.List;

@Configuration
@ComponentScan("com.itsm.course.hw2")
@PropertySource(value = "classpath:server.properties")
public class Config {

    @Value("${port}")
    private Integer port;
    @Value("${thread.count}")
    private Integer threadCount;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ServerService serverService(
            ObjectMapper mapper, Provider<List<RequestProcessor>> processor) {
        return new ServerService(port, threadCount, mapper, processor);
    }

}