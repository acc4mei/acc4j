package com.acc4j.jdk8.streamAPI;

import org.junit.Test;

public class StreamAPISampleTest {

    @Test
    public void streamAPI() {
        StreamAPISample streamAPISample = new StreamAPISample();

        streamAPISample.create();
        streamAPISample.basicOps();
        streamAPISample.filterOps();
        streamAPISample.mapReduceOps();
        streamAPISample.collectorsOps();
    }
}