package com.acc4j.po;

import com.acc4j.jdk8.annotation.Sample;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Sample("on Child1")
@Sample("on Child2")
@EqualsAndHashCode(callSuper = true)
@Data
public class Child extends Parent {
}
