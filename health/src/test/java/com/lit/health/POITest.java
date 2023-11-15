package com.lit.health;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;


public class POITest {
    //使用poi读取excel文件的数据
    @Test
    public void test1() throws Exception {
        //加载指定文件，创建一个excel对象
        XSSFWorkbook excel = new XSSFWorkbook(new File("D:\\BaiduNetdiskDownload\\6、传智健康项目\\资料-传智健康项目\\day05\\资源\\预约设置模板文件\\test.xlsx"));
        //读取excel文件中第一个sheet标签页
        XSSFSheet sheet = excel.getSheetAt(0);
        //遍历Sheet标签页,获得每一行数据
        for (Row row : sheet) {
            //遍历每一行,获取每个单元格对象;
            for (Cell cell : row) {
                System.out.println(cell.getStringCellValue());
            }
        }
        //关闭资源
        excel.close();
    }

    //升级版使用poi读取excel文件的数据
    @Test
    public void test2() throws Exception {
        //加载指定文件，创建一个excel对象
        XSSFWorkbook excel = new XSSFWorkbook(new File("D:\\BaiduNetdiskDownload\\6、传智健康项目\\资料-传智健康项目\\day05\\资源\\预约设置模板文件\\test.xlsx"));
        //读取excel文件中第一个sheet标签页
        XSSFSheet sheet = excel.getSheetAt(0);
        //获取当前工作表最后一个行号,行号从0开始
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 0; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                XSSFCell cell = row.getCell(j);
                System.out.println(cell.getStringCellValue());
            }
        }
        //关闭资源
        excel.close();
    }

    //使用POI向excel文件写入数据,并且通过输入流将创建excel文件保存到本地磁盘
    @Test
    public void test3() throws Exception {
        //加载指定文件，创建一个excel对象
        XSSFWorkbook excel = new XSSFWorkbook();
        //创建一个工作表
        XSSFSheet sheet = excel.createSheet("lit");
        //创建行
        XSSFRow row1 = sheet.createRow(0);
        //在行中,创建单元格对象
        row1.createCell(0).setCellValue("姓名");
        row1.createCell(1).setCellValue("地址");
        row1.createCell(2).setCellValue("年龄");
        XSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("张三");
        row2.createCell(1).setCellValue("很难");
        row2.createCell(2).setCellValue("20");
        //创建输出流，通过输出流将内存的excel文件写道磁盘
        FileOutputStream out=new FileOutputStream(new File("D:\\zhuomian\\洛阳理工毕业设计\\test.xlsx"));
        excel.write(out);//写入文件
        out.flush();//刷新数据

        //关闭资源
        excel.close();
    }

}
