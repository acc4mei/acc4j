package com.acc4j.jdk8.methodInterface;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Lambda表达式：以匿名函数的形式“实现”函数式接口
 * Lambda简化了匿名内部类形式，但实现原理并不同；匿名内部类在编译过程种会产生类；Lambda则是调用JVM的 invokedynamic 指令实现，不会产生类
 */
public class LambdaExpressionSample {

    /**
     * Lambda结构：(参数列表) -> {方法体}
     * 语法：
     * 1. 参数列表的的小括号不能省略
     * 2. 方法体只有一行时可省略花括号；一行为返回值时可省略return；没有内容或者多行内容时花括号不能省略
     * 3. 支持函数式接口调用对应方法实现
     */
    public void sample() throws Exception {
        System.out.println("--------------- LambdaExpression sample --------------");

        // 不能省略{}
        Runnable l0 = () -> {
        };
        Runnable l1 = () -> System.out.println("无参数，无返回值");
        // 可以省略return
        Callable<String> l2 = () -> "无参数，有返回值";
        Consumer<String> l3 = (String a) -> System.out.println("有参数，无返回值");
        Function<String, String> l4 = (String a) -> "有参数，有返回值";
        // 自定义函数式接口，多个参数，无返回值；用lambda来达成实现并调用
        IFunctionSample<String, String> l5 = (String a, String b) -> System.out.println("自定义函数式接口，多个参数，无返回值");

        l0.run();
        l1.run();
        System.out.println(l2.call());
        l3.accept("l3");
        System.out.println(l4.apply("l4"));
        l5.sample("l", "5");
        l5.desc();
        System.out.println();
    }
}
