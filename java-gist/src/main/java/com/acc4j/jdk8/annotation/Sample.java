package com.acc4j.jdk8.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    @Documented 元注解，表示注释信息纳入JavaDoc

    @Target 元注解，表示注解的目标类型，值类型 ElementType[]；没有声明时支持所有类型
        JDK8新增TYPE_USE，TYPE_PARAMETER的类型注解

    @Retention 元注解，表示注解保留周期，值类型 RetentionPolicy；没有声明时，默认CLASS
        SOURCE  只保留在源码中，纯标记
        CLASS  保留到Class文件中，但是【JVM不会持有该类别信息，所以反射也无法获取】
        RUNTIME  保留到Class文件，JVM会持有该类别信息，支持反射获取

    @Inherited 元注解，表示注解信息可顺延着被标注元素的父子关系进行获取；注意，注解之间不存在继承关系

    @Repeatable JDK8新增的元注解，表示注解可重复标注，实质上必须提供一个容器注解进行容纳。
        容器注解注意事项：
        ① 必须含有目标注解类型数组的成员
        ② Target范围为目标注解的子集
        ③ Retention范围大于目标注解
        ④ 目标注解标注@Documented，@Inherited时，容器也需要对应标注
        ⑤ 被标注元素上的目标注解的值和@Inherited获取的值【不会被视为同组的值】，只有复数个目标注解才会纳入容器

    参考资料：https://blog.csdn.net/javazejian/article/details/71860633
*/
@Documented
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Repeatable(value = SampleContainer.class)
public @interface Sample {
    /*
        注解成员，可以定义多个，合法的类型有基本类型、String、Class、枚举、注解、以及这些类型的数组
        在使用含有成员的注解时必须保证成员有值，显式赋值或默认值，默认值必须为常量内容

        语法糖：成员名取为value并且只赋值该成员时，可直接 @Simple("xxx") 赋值
     */
    String value() default "";

    String other() default "";
}
