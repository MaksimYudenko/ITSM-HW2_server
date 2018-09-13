package by.itsm.course.hw2s.core.proccessors;

import by.itsm.course.hw2s.core.model.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

@RunWith(MockitoJUnitRunner.class)
public class ExtendedRequestProcessorTest {

    @Value("${delay}")
    long delay;

    @InjectMocks
    private ExtendedRequestProcessor extendedRequestProcessor;

    @Test
    public void isAdmissibleTest() {
        Request req1 = new Request("Max", "Hello, server");
        Request req2 = new Request("Max", "Hello there");
        Assert.isTrue(extendedRequestProcessor.isAdmissible(req2), "it's OK  )");
        Assert.isTrue(!extendedRequestProcessor.isAdmissible(req1), "something went wrong  (");
    }

}