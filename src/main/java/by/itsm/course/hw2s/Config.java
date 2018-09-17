package by.itsm.course.hw2s;

import by.itsm.course.hw2s.core.Server;
import by.itsm.course.hw2s.core.proccessors.RequestProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.inject.Provider;
import java.util.List;

@Configuration
@ComponentScan("by.itsm.course.hw2s")
@PropertySource("classpath:server.properties")
public class Config {

    @Value("${port}")
    private Integer port;
    @Value("${thread.count}")
    private Integer threadCount;

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    @Bean
    public Server serverService(ObjectMapper mapper, Provider<List<RequestProcessor>> processor) {
        return new Server(port, threadCount, mapper, processor);
    }

}