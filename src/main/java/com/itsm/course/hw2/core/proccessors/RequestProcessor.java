package com.itsm.course.hw2.core.proccessors;

import com.itsm.course.hw2.dto.Request;

public interface RequestProcessor {

    String process(Request request);

}