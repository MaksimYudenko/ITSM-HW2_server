package by.itsm.course.hw2s.core.proccessors;

import by.itsm.course.hw2s.core.model.Request;

public interface RequestProcessor {

    String process(Request request);

    boolean isAdmissible(Request request);

}