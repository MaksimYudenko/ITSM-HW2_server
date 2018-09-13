package by.itsm.course.hw2s.core.proccessors;

import by.itsm.course.hw2s.core.model.Request;
import by.itsm.course.hw2s.util.sleeper.ThreadSleeperImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BaseRequestProcessorTest {

    @Value("${delay}")
    long delay;

    @Mock
    private ThreadSleeperImpl sleeper;

    @InjectMocks
    private BaseRequestProcessor baseRequestProcessor;

    @Test
    public void isAdmissibleTest() {
        Request rightRequest = new Request("Max", "Hello, Server!");
        Request wrongRequest = new Request("Max", "Hello specified, Server!");

        Assert.isTrue(baseRequestProcessor.isAdmissible(rightRequest), "Wrong request");
        Assert.isTrue(!baseRequestProcessor.isAdmissible(wrongRequest), "Wrong request");

        String response = baseRequestProcessor.process(rightRequest);

        Assert.notNull(response, "Can't process request");

        verify(sleeper, times(1)).sleep(delay);
    }

}