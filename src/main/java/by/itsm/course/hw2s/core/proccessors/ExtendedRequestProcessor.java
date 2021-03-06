package by.itsm.course.hw2s.core.proccessors;

import by.itsm.course.hw2s.core.model.Request;
import by.itsm.course.hw2s.util.sleeper.ThreadSleeper;
import by.itsm.course.hw2s.util.sleeper.ThreadSleeperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
public class ExtendedRequestProcessor implements RequestProcessor {

    @Value("${delay}")
    private Long delay;
    private final ThreadSleeper sleeper = new ThreadSleeperImpl();

    @Override
    public String process(Request request) {
        String name = request.getName();
        sleeper.sleep(delay);
        System.out.println(String.format("%s's new message: %s", name, request.getMessage()));
        return "Hello, " + name + " from ExtendedRequestProcessor";
    }

    @Override
    public boolean isAdmissible(Request request) {
        return request != null & !Objects.requireNonNull(request).getMessage().contains("server");
    }

}