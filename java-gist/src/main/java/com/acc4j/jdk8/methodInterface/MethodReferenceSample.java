package com.acc4j.jdk8.methodInterface;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 方法引用：以具名函数的形式“实现”函数式接口
 * 可以访问到的方法（包括构造方法）都能化作方法引用，从而作为函数式接口使用
 */
public class MethodReferenceSample {

    /**
     * 方法引用结构：类，接口，对象::方法名
     * 1. 方法引用的参数是隐含的，与对应的函数式接口匹配
     * 2. 支持函数式接口调用对应方法实现
     */
    public void sample() throws Exception {
        System.out.println("--------------- MethodReference sample --------------");

        Consumer<String> m1 = System.out::println;
        BiFunction<Collection<String>, Object, Boolean> m2 = Collection<String>::contains;
        Callable<String> m3 = "a"::toUpperCase;
        Function<String, String> m4 = String::new;

        m1.accept("sout!");
        Boolean apply = m2.apply(Arrays.asList("a", "b", "c"), "a");
        System.out.println("判断元素是否存在：" + apply);
        System.out.println("变换大写：" + m3.call());
        System.out.println("创建字符串：" + m4.apply("new str"));
        System.out.println();
    }
}
