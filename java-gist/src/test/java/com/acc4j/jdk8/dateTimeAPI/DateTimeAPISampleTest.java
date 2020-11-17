package com.acc4j.jdk8.dateTimeAPI;

import org.junit.Test;

public class DateTimeAPISampleTest {

    @Test
    public void dateTimeAPI() {
        DateTimeAPISample dateTimeAPISample = new DateTimeAPISample();

        dateTimeAPISample.instant();
        dateTimeAPISample.localDateTime();
        dateTimeAPISample.dateTimeFormatter();
        dateTimeAPISample.periodAndDuration();
        dateTimeAPISample.zonedDateTime();
    }
}