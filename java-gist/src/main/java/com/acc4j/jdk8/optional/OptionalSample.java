package com.acc4j.jdk8.optional;

import com.acc4j.po.User;

import java.util.Optional;
import java.util.Random;

/**
 * Optional用于减少编码中出现NPE处理的情况，但不能完全避免NPE
 */
public class OptionalSample {

    /**
     * empty
     * of
     * ofNullable
     */
    public void create() {
        // 空内容操作会产生NoSuchElementException
        Optional<Object> o1 = Optional.empty();
        // 默认接收非null值，否则直接抛出NPE
        Optional<Integer> o2 = Optional.of(1);
        Optional<Object> o3 = Optional.ofNullable(null);

        System.out.println(o1);
        System.out.println(o2);
        System.out.println(o3);
    }

    /**
     * isPresent，get
     * ifPresent
     * orElse
     * orElseGet
     * orElseThrow
     */
    public void getOps() {
        System.out.println("--------------- Optional getOps --------------");
        Random random = new Random();
        int num = random.nextInt(100);
        String unackVal = num >= 50 ? "existString" : null;
        User user = num >= 50 ? new User("existUser") : null;

        Optional<String> o1 = Optional.ofNullable(unackVal);
        // present判断后函数式接口Consumer消费值
        if (o1.isPresent()) {
            System.out.println("isPresent 有值：" + o1.get());
        } else {
            System.out.println("isPresent 无值");
        }
        o1.ifPresent(s -> System.out.println("ifPresent 有值（Consumer）：" + s));
        // 组合使用
        String s1 = Optional.ofNullable(unackVal).orElse("empty!");
        System.out.println("有值ofNullable， 无值orElse：" + s1);

        /*
            orElse 与 orElseGet 差异
            1. null值时行为相同，执行orElse，orElseGet内容返回默认值
            2. 有值时，都返回设定的值，但是orElse内容仍然会执行（“if-else都执行”的情景，结果被忽略，性能损失）！；orElseGet内容不会执行！
         */
        User u1 = Optional.ofNullable(user).orElse(new User("u1"));
        System.out.println("user：" + user + "，orElse执行情况 u1：" + u1);
        User u2 = Optional.ofNullable(user).orElseGet(() -> new User("u2"));
        System.out.println("user：" + user + "，orElseGet执行情况 u2：" + u2);

        // 自定义空值处理异常
        // Optional.ofNullable(unackVal).orElseThrow(() -> new IllegalArgumentException("unackVal 值为空"));
        System.out.println();
    }

    /**
     * map
     * flatMap
     */
    public void valueOps() {
        System.out.println("--------------- Optional valueOps --------------");
        Random random = new Random();
        User u1 = random.nextInt(100) >= 50 ? new User("B1", "男", random.nextInt(50), "xxx") : null;

        String s1 = Optional.ofNullable(u1).map(User::getName).orElse("default name");
        String s2 = Optional.ofNullable(u1).flatMap(User::getAddress).orElse("default address");
        Optional<User> o1 = Optional.ofNullable(u1).filter(user -> user.getAge() > 25);

        System.out.println("user：" + u1 + " map取字段值：" + s1);
        System.out.println("user：" + u1 + " flatMap解除包装，扁平化获取字段值：" + s2);
        System.out.println("user 是否大于25岁，filter，是则返回值：" + o1 + "（否则返回empty Optional）");
        System.out.println();
    }
}
