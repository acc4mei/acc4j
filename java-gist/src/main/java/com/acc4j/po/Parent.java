package com.acc4j.po;

import com.acc4j.jdk8.annotation.Sample;
import lombok.Data;

@Sample("on Parent")
@Data
public class Parent {

    public void repeat(@Sample("on Parent Method Parameter") String msg) {
        System.out.println(msg);
    }
}
