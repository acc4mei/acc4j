package com.acc4j.fel;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Fel是轻量级的高效的表达式计算引擎，语法与Java基本相同；Fel不支持脚本
 * https://code.google.com/archive/p/fast-el/
 */
public class FelAPISample {

    /**
     * FelEngine
     * FelContext
     */
    public void felSample() {
        FelEngine fe = new FelEngineImpl();

        // 算数表达式
        String e1 = "((7 + 14 * 27) / 103) % 4";
        Object r1 = fe.eval(e1);

        // 设置变量，可以访问对象，map，数组，list
        FelContext c1 = fe.getContext();
        c1.set("a", 123);
        c1.set("b", 456);
        String e2 = "a / b";
        Object r2 = fe.eval("a / b");

        FelContext c2 = fe.getContext();
        Map<String, Number> map = new HashMap<>();
        map.put("k1", 112.36);
        map.put("k2", -11.23);
        map.put("k3", 37.995);
        c2.set("map", map);
        String e3 = "(map.k1 + map.k2) * map.k3";
        Object r3 = fe.eval(e3);

        FelContext c3 = fe.getContext();
        List<? extends Number> nums = Arrays.asList(1567, 4654, 146546);
        c3.set("nums", nums);
        String e4 = "nums[0] * nums[1] / nums[2]";
        Object r4 = fe.eval(e4);

        // 调用方法（注意安全管理器，不要执行高危代码）
        FelContext c4 = fe.getContext();
        c4.set("FelAPISample", this);
        c4.set("a", 7);
        c4.set("b", 8);
        String e6 = "FelAPISample.samplesSum(a, b)";
        Object r6 = fe.eval(e6);

        System.out.println(e1 + " = " + r1);
        System.out.println(e2 + " = " + r2);
        System.out.println(e3 + " = " + r3);
        System.out.println(e4 + " = " + r4);
        System.out.println(e6 + " = " + r6);
        System.out.println();
    }

    public int samplesSum(int a, int b) {
        return a + b;
    }
}
