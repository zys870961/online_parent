package com.shangguigu.eduservice.pojo;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {

    public static void main(String[] args) {
       //实现excel写操作
     /*   String filename="E:\\01.xls";
        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());*/

        //实现excel读操作
        String filename = "E:\\01.xls";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    //创建方法返回list集合

    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return list;
    }

}
