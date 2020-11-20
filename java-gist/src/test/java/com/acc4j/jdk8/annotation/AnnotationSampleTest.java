package com.acc4j.jdk8.annotation;

import com.acc4j.po.Child;
import com.acc4j.po.Parent;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationSampleTest {

    @Test
    public void annotationSample() throws NoSuchMethodException {
        String v1 = Parent.class.getAnnotation(Sample.class).value();
        String v2 = Child.class.getAnnotation(Sample.class).value();
        Sample[] samples = Child.class.getAnnotation(SampleContainer.class).value();
        List<String> v3 = Arrays.stream(samples).map(Sample::value).collect(Collectors.toList());
        // 获取类型注解，更加细粒度的进行代码元数据分析
        Method repeat = Parent.class.getMethod("repeat", String.class);

        System.out.println("获取Parent标注的@Sample：" + v1);
        System.out.println("获取Child标注的@Inherited继承Parent获取的@Sample：" + v2);
        System.out.println("获取Child重复标注的多个@Sample后自动装入容器注解@SampleContainer：" + v3);
        System.out.println("获取Parent的方法上标注的@Sample：" + Arrays.deepToString(repeat.getParameterAnnotations()));
    }
}
