package com.acc4j.jdk8.optional;

import org.junit.Test;

public class OptionalSampleTest {

    @Test
    public void optionalSample() {
        OptionalSample optionalSample = new OptionalSample();

        optionalSample.create();
        optionalSample.getOps();
        optionalSample.valueOps();
    }
}