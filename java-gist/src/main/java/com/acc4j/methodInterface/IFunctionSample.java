package com.acc4j.methodInterface;

/**
 * 函数式接口：标注了@FunctionalInterface，有且仅有一个抽象方法的接口
 */
@FunctionalInterface
public interface IFunctionSample<A, B> {
    void sample(A a, B b);

    default void desc() {
        System.out.println("函数式接口可以有default方法；default方法具备方法体，不会导致匿名实现产生歧义");
    }
}
