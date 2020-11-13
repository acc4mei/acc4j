package com.acc4j.methodInterface;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 方法引用：以具名函数的形式“实现”函数式接口。
 * 可以访问到的函数都能化作方法引用，可以作为函数式接口类型的参数使用
 */
public class MethodReferenceSample {

    /**
     * 方法引用结构：类名（接口名）::方法名
     * 1. 参数是隐含的，与对应的函数式接口匹配
     * 2. 函数式接口可以引用具名函数
     */
    public void sample() {
        // 函数式接口可以引用具名函数
        Consumer<String> m1 = System.out::println;
        Comparator<Integer> m2 = Integer::compare;

        // 函数式接口调用方法引用
        m1.accept("sout!");
        System.out.println("compare! " + m2.compare(1, 2));
    }
}
