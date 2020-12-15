package com.acc4j.easyExcel;

import com.acc4j.po.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EasyExcelSampleTest {

    @Test
    public void easyExcelSample() {
        EasyExcelSample.simpleWrite(getUserList(), "simple.xlsx", "simple");
        EasyExcelSample.sheetWriter(getUserListList(), "sheet.xlsx");

        List<User> r1 = EasyExcelSample.simpleRead("simple.xlsx", "simple");
        List<User> r2 = EasyExcelSample.sheetRead("sheet.xlsx");
        System.out.println(r1);
        System.out.println(r2);
    }

    private List<User> getUserList() {
        List<User> dataList = new ArrayList<>();
        Random random = new Random();
        int size = random.nextInt(10);
        for (int i = 0; i < size; i++) {
            User user = User.builder()
                    .name("tu" + i)
                    .age(i)
                    .sex(i > (size / 2) ? "男" : "女").build();
            dataList.add(user);
        }
        return dataList;
    }

    private List<List<User>> getUserListList() {
        List<List<User>> list = new ArrayList<>();
        Random random = new Random();
        int size = random.nextInt(10);
        for (int i = 0; i < size; i++) {
            list.add(getUserList());
        }
        return list;
    }
}