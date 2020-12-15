package com.acc4j.po;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("性别")
    private String sex;

    @ExcelProperty("年龄")
    private int age;

    @ExcelIgnore
    private String address;

    @ExcelProperty("创建时间")
    private Date createTime;

    public User(String name) {
        this.name = name;
        System.out.println("create new user[ " + name + " ]");
    }

    public User(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public User(String name, String sex, int age, String address) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.address = address;
    }

    public Optional<String> getAddress() {
        return Optional.ofNullable(address);
    }
}
