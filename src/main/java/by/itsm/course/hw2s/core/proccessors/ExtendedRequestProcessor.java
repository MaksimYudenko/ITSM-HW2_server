package by.itsm.course.hw2s.core.proccessors;

import by.itsm.course.hw2s.core.model.Request;
import by.itsm.course.hw2s.util.sleeper.ThreadSleeper;
import by.itsm.course.hw2s.util.sleeper.ThreadSleeperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ExtendedRequestProcessor implements RequestProcessor {

    @Value("${delay}")
    private Long delay;
    private final ThreadSleeper sleeper = new ThreadSleeperImpl();

    @Override
    public String process(Request request) {
        String message = request.getMessage();
        String name = request.getName();
        sleeper.sleep(delay);
        System.out.println(String.format("%s's new message: %s", name, message));
        return "Hello, " + name + " from ExtReqProc";
    }

    @Override
    public boolean isAdmissible(Request request) {
        return request != null && "exit".equalsIgnoreCase(request.getMessage());
    }

}