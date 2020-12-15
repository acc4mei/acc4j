package com.acc4j.easyExcel;

import com.acc4j.po.User;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * AnalysisEventListener每次读取excel都要new，不能被Spring管理
 * 业务处理中需要使用到SpringBean时，构造器传入使用。
 */
@Slf4j
@Data
public class EasyExcelUserListener extends AnalysisEventListener<User> {

    private static final int DATA_ROW_INDEX = 1;

    private List<User> userList = new ArrayList<>();

    /**
     * 每一行解析
     */
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        Integer rowIndex = analysisContext.readRowHolder().getRowIndex();
        if (1 <= rowIndex) {
            userList.add(user);
        }
    }

    /**
     * 全部执行后
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("解析sheet【 {}】完毕，累计解析 {} 条记录", analysisContext.readSheetHolder().getSheetName(), userList.size());
    }
}
