package com.acc4j.methodInterface;

import org.junit.Test;

public class IFunctionSampleTest {

    @Test
    public void functionInterface() throws Exception {
        LambdaExpressionSample lambdaExpressionSample = new LambdaExpressionSample();
        lambdaExpressionSample.sample();

        MethodReferenceSample methodReferenceSample = new MethodReferenceSample();
        methodReferenceSample.sample();
    }
}