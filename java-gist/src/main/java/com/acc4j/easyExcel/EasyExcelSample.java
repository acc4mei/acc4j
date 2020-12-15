package com.acc4j.easyExcel;

import com.acc4j.po.User;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * 样式，合并单元格，图片，表格等内容参考：
 * https://www.yuque.com/easyexcel/doc/easyexcel
 */
public class EasyExcelSample {

    public static void simpleWrite(List<User> dataList, String fileName, String sheetName) {
        // User类字段标注EasyExcel的注解来控制表头，字段的显隐
        EasyExcel.write(new File("target/" + fileName), User.class).sheet(sheetName).doWrite(dataList);
    }

    public static void sheetWriter(List<List<User>> sheetDataList, String fileName) {
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write("target/" + fileName, User.class).build();
            int count = 1;
            for (List<?> sheetData : sheetDataList) {
                WriteSheet sheet = EasyExcel.writerSheet("sheet" + count).build();
                excelWriter.write(sheetData, sheet);
                count++;
            }
        } finally {
            if (Objects.nonNull(excelWriter)) {
                excelWriter.finish();
            }
        }
    }

    public static List<User> simpleRead(String fileName, String sheetName) {
        EasyExcelUserListener userListener = new EasyExcelUserListener();
        EasyExcel.read(new File("target/" + fileName), User.class, userListener).sheet(sheetName).doRead();
        return userListener.getUserList();
    }

    public static List<User> sheetRead(String fileName) {
        EasyExcelUserListener userListener = new EasyExcelUserListener();
        // 注意 doAfterAllAnalysed 会在每个sheet读取完毕后调用一次。然后所有sheet都会往同一个DemoDataListener里面写
        EasyExcel.read(new File("target/" + fileName), User.class, userListener).doReadAll();
        return userListener.getUserList();
    }
}
