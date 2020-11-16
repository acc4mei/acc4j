package com.acc4j.jdk8.streamAPI;

import com.acc.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * StreamAPI：高效的可迭代容器数据处理流式API，支持并发流。
 * Stream是数据流从开始，数据操作，到结束的完整流程，不能反复使用。
 * 多个Stream能连接成一个”大的“Stream，数据流像使用管道一样"拼接"进行一系列的数据操作。
 * Stream不会改变源容器的数据，保障了源数据安全。
 */
public class StreamAPISample {

    /**
     * StreamAPI操作的对象为可迭代类型
     */
    public void create() {
        // SteamAPI直接创建Steam
        Stream<Integer> s1 = Stream.of(1, 2, 3);
        Stream<Object> s2 = Stream.empty();
        // 连接两个Stream产生新的Stream，不影响源Stream
        Stream<Object> s3 = Stream.concat(s1, s2);

        // 数组，可迭代的集合间接创建Steam
        Stream<Integer> s4 = Arrays.stream(new Integer[]{1, 2, 3});
        Stream<String> s5 = new ArrayList<String>().stream();

        // 并发Stream
        s1 = s1.parallel();
        Stream<String> s6 = new ArrayList<String>().parallelStream();
    }

    /**
     * forEach
     * forEachOrdered
     * sorted
     * toArray
     */
    public void basicOps() {
        List<String> list = getStringList();
        System.out.println("示例数据：" + list);
        System.out.println("--------------- forEachOps --------------");

        // List内置forEach逻辑，可省去stream()，本质peek操作，处理Consumer函数接口操作
        list.stream().forEach(s -> System.out.println("forEach 消费元素，无返回值（转换大写）：" + s.toUpperCase()));
        list.parallelStream().forEachOrdered(s -> System.out.println("forEachOrdered 并发流时保证顺序消费元素，无返回值（转换大写）：" + s.toUpperCase()));
        // 支持多字段排序
        List<String> r1 = list.stream().sorted().collect(Collectors.toList());
        // List内置toArray逻辑，可省去stream()，本质IntFunction<R>，给定int参数（长度）返回所需类型数组
        String[] r2 = list.stream().toArray(String[]::new);

        System.out.println("sorted 可比性排序：" + r1);
        System.out.println("toArray 转换数组：" + Arrays.toString(r2));
        System.out.println();
    }

    /**
     * filter
     * distinct
     * findFirst
     * findAny
     * anyMatch
     * max
     * min
     * skip
     * limit
     */
    public void filterOps() {
        System.out.println("--------------- filterOps --------------");
        List<String> list = getStringList();

        List<String> r1 = list.stream().filter(s -> s.compareTo("c") < 0).collect(Collectors.toList());
        List<String> r2 = list.stream().distinct().collect(Collectors.toList());
        Optional<String> r3 = list.stream().findFirst();
        Optional<String> r4 = list.stream().findAny();
        boolean r5 = list.stream().anyMatch("b"::equals);
        Optional<String> r6 = list.stream().max(String::compareTo);
        Optional<String> r7 = list.stream().min(String::compareTo);
        List<String> r8 = list.stream().skip(2).collect(Collectors.toList());
        List<String> r9 = list.stream().limit(3).collect(Collectors.toList());

        System.out.println("filter，根据条件过滤（小于c）：" + r1);
        System.out.println("distinct，过滤重复内容：" + r2);
        System.out.println("findFirst，获取第一个元素：" + r3.get());
        System.out.println("findAny，获取任意一个元素：" + r4.get());
        System.out.println("anyMatch，判断是否有任意元素匹配过滤条件（等于b）" + r5);
        System.out.println("max，取最大值：" + r6.get());
        System.out.println("min，取最小值：" + r7.get());
        System.out.println("skip，跳过2个元素：" + r8);
        System.out.println("limit，限制3个元素：" + r9);
        System.out.println();
    }

    /**
     * peek
     * map
     * mapToXXX
     * flatMap
     * flatMapToXXX
     * reduce
     */
    public void mapReduceOps() {
        System.out.println("--------------- mapReduceOps --------------");
        List<String> list = getStringList();

        list.stream().peek(s -> System.out.println("peek 消费元素，无返回值（转换大写）：" + s.toUpperCase())).close();
        List<String> r1 = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> l1 = getStringList();
        List<String> l2 = getStringList();
        List<String> l3 = getStringList();
        List<List<String>> listList = Arrays.asList(l1, l2, l3);
        List<String> r2 = listList.stream().flatMap(Collection::stream).map(String::toUpperCase).collect(Collectors.toList());
        Optional<String> r3 = list.stream().reduce((s, s2) -> s.concat("-").concat(s2));

        System.out.println("map 转换元素，有返回值（转换大写并形成新的集合）：" + r1);
        System.out.println("flatMap 对二维可迭代容器Stream进行扁平化转换，\"水平铺开\" 成一个Steam后续进行操作（集体转换大写并形成新的集合）：" + r2);
        System.out.println("reduce 容器至少两个元素，两个元素进行reduce操作，返回的结果与下一个元素继续reduce（短横拼接字符串）：" + r3);
        System.out.println();
    }

    /**
     * toList
     * toSet
     * joining
     * groupingBy
     * partitioningBy
     */
    public void collectorsOps() {
        System.out.println("--------------- collectorsOps --------------");
        List<User> list = getUserList();

        List<User> r1 = list.stream().collect(Collectors.toList());
        Set<User> r2 = list.stream().collect(Collectors.toSet());
        String r3 = list.stream().map(User::getSex).collect(Collectors.joining("-"));
        // 分区分组支持嵌套多级操作
        Map<String, List<User>> r4 = list.stream().collect(Collectors.groupingBy(User::getSex));
        Map<Boolean, List<User>> r5 = list.stream().collect(Collectors.partitioningBy(user -> user.getAge() <= 23));

        System.out.println("toList()，转换成列表：" + r1);
        System.out.println("toSet()，转换成集合：" + r2);
        System.out.println("joining()，字符串拼接：" + r3);
        System.out.println("groupingBy()，数据分组（按性别）：" + r4);
        System.out.println("partitioningBy()，数据分区（按年龄判断）：" + r5);
        System.out.println();
    }

    private List<String> getStringList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("a");
        return list;
    }

    private List<User> getUserList() {
        List<User> list = new ArrayList<>();
        list.add(new User("男1", "男", 21));
        list.add(new User("男2", "男", 22));
        list.add(new User("男3", "男", 23));
        list.add(new User("女1", "女", 24));
        list.add(new User("女2", "女", 25));
        return list;
    }
}
