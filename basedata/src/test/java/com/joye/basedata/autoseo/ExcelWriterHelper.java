package com.joye.basedata.autoseo;

import com.joye.basedata.ExcelUtils;
import com.joye.basedata.filter.ReplaceDomainEntity;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joye on 2017/4/4.
 *
 * 写入Excel帮助类
 */

public class ExcelWriterHelper extends ExcelUtils{


    /**
     * 写入外部资源
     */
    public static void WriteExtralRerouse(List<UploadRowResourceEntity> mResourceEntities, String excelPath, String fileName) throws IOException {
//        String excelPath ="/Users/joye/downloads/";
//        String fileName="joye";
        //创建Excel文件
        Workbook workbook= createWorkBook(excelPath,fileName,EXCEL_TYPE_XLSX);
        //根据资源,写入内容
        writeContent(workbook,mResourceEntities);
        excelPath=excelPath+ File.separator+fileName+"."+EXCEL_TYPE_XLSX;
        //创建文件流
        OutputStream stream = new FileOutputStream(excelPath);
        //写入数据
        workbook.write(stream);
        //关闭文件流
        stream.close();
    }

    /**
     * 写入上传检查
     *
      */
    public static void writeUploadCheck(List<UploadRowResourceEntity> mResourceEntities,
                                        String excelPath,
                                        String fileName,
                                        List<String>ips,
                                        List<ReplaceDomainEntity> replaceDomainEntities) throws IOException {
        //创建Excel文件
        Workbook workbook= createWorkBook(excelPath,fileName,EXCEL_TYPE_XLSX);
        //根据资源,写入内容
        writeContent(workbook,mResourceEntities);
        //写入排版专用
        writeTypesetting(workbook,mResourceEntities,ips);
        //写入备用检查对象站
        writeNeedReplaces(workbook,replaceDomainEntities);
        excelPath=excelPath+ File.separator+fileName+"."+EXCEL_TYPE_XLSX;
        //创建文件流
        OutputStream stream = new FileOutputStream(excelPath);
        //写入数据
        workbook.write(stream);
        //关闭文件流
        stream.close();
    }

    /**
     *
     * @param workbook
     * @param resourceEntities
     */

    private static void writeContent(Workbook workbook, List<UploadRowResourceEntity> resourceEntities){
        Sheet sheet =workbook.createSheet("上站文档");
        //获取样式
        CellStyle mCellStyle=CreateCellStyle(workbook);
        //写入第一行
        createTitle(sheet,mCellStyle);
        //从第二行开始写入数据
        writeResourceEntityCellDataToRow(sheet,1,resourceEntities);

    }

    public static void writeNeedReplaces(Workbook workbook, List<ReplaceDomainEntity> resourceEntities){
        Sheet sheet =workbook.createSheet("备用站替换");
        //获取样式
        CellStyle mCellStyle=CreateCellStyle(workbook);
        //创建第一行
        Row row = sheet.createRow(0);
        String[]titleRow=new String[]{"对象站","关键词*","编码"};
        Cell cell=null;
        for (int i =0;i<titleRow.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(titleRow[i]);
            cell.setCellStyle(mCellStyle); // 样式，居中
            sheet.setColumnWidth(i, 20 * 256);
        }
        row.setHeight((short) 540);
        for (int i = 0; i < resourceEntities.size(); i++) {
            ReplaceDomainEntity entiy= resourceEntities.get(i);
            row = (Row) sheet.createRow(i+1);
            row.setHeight((short) 500);
            row.createCell(0).setCellValue(entiy.getDomain());
            row.createCell(1).setCellValue(entiy.getKeystr());
            row.createCell(2).setCellValue(entiy.getCharsert());
        }
    }


    /**
     * 写入排版专用
     * @param oldmains 老域名
     * @param ips
     */
    private static void writeTypesetting(Workbook workbook,List<UploadRowResourceEntity> oldmains,List<String> ips){
        Sheet sheet =workbook.createSheet("排版专用");
        //获取样式
        CellStyle mCellStyle=CreateCellStyle(workbook);
        //循环写入行数据
        Row row=null;
        for (int i = 0; i < oldmains.size(); i++) {
            String oldDomain= oldmains.get(i).getOldDomainNoFrefix();
            row = (Row) sheet.createRow(i);
            row.setHeight((short) 500);
            row.createCell(0).setCellValue("www."+oldDomain);
            row.createCell(1).setCellValue("#");
            row.createCell(2).setCellValue(ips.get(i));
            row.createCell(3).setCellValue("www."+oldDomain);
            row.createCell(4).setCellValue("#");
            row.createCell(5).setCellValue(ips.get(i));
            row.createCell(6).setCellValue(oldDomain);
            row.createCell(7).setCellValue("");
            row.createCell(8).setCellValue("");
            row.createCell(9).setCellValue("http://");
            row.createCell(10).setCellValue("http://www."+oldDomain);
            row.createCell(11).setCellValue("");
            row.createCell(12).setCellValue(oldDomain);
            row.createCell(13).setCellValue("|");
            row.createCell(14).setCellValue("@");
            row.createCell(15).setCellValue("|");
            row.createCell(16).setCellValue("A");
            row.createCell(17).setCellValue("|");
            row.createCell(18).setCellValue(ips.get(i));
            row.createCell(19).setCellValue("|");
            row.createCell(20).setCellValue("默认");
            row.createCell(21).setCellValue("我就是要分割");
            row.createCell(22).setCellValue(oldDomain);
            row.createCell(23).setCellValue("|");
            row.createCell(24).setCellValue("*");
            row.createCell(25).setCellValue("|");
            row.createCell(26).setCellValue("A");
            row.createCell(27).setCellValue("|");
            row.createCell(28).setCellValue(ips.get(i));
            row.createCell(29).setCellValue("|");
            row.createCell(30).setCellValue("默认");
            row.createCell(31).setCellValue("你拿我咋滴");
            row.createCell(32).setCellValue(oldDomain);
            row.createCell(33).setCellValue("|");
            row.createCell(34).setCellValue("www");
            row.createCell(35).setCellValue("|");
            row.createCell(36).setCellValue("A");
            row.createCell(37).setCellValue("|");
            row.createCell(38).setCellValue(ips.get(i));
            row.createCell(39).setCellValue("|");
            row.createCell(40).setCellValue("默认");
            ;
//            row.createCell(9).setCellValue(getObjectDomain());
//            row.createCell(10).setCellValue(getReplaceKeyStr());
//            row.createCell(11).setCellValue(getChartsetStr());
        }

    }



    /**
     * 写入数据到单元行
     * @param sheet 工作薄
     * @param beginIndex 开始写入的位置
     * @param resourceEntities 数据源
     *
     */
    private static void writeResourceEntityCellDataToRow(Sheet sheet, int beginIndex, List<UploadRowResourceEntity> resourceEntities){
        //循环写入行数据
        Row row=null;
        for (int i = 0; i < resourceEntities.size(); i++) {
            UploadRowResourceEntity entiy= resourceEntities.get(i);
            row = (Row) sheet.createRow(i+beginIndex);
            row.setHeight((short) 500);
            entiy.setRow(row);
        }
    }


    /**
     * 创建表头
     */
    private static void createTitle(Sheet  sheet,CellStyle mCellStyle){
        //创建第一行
        Row row = sheet.createRow(0);
        String[]titleRow= UploadRowResourceEntity.TITLES;
        Cell cell=null;
        for (int i =0;i<titleRow.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(titleRow[i]);
            cell.setCellStyle(mCellStyle); // 样式，居中
            sheet.setColumnWidth(i, 20 * 256);
        }
        row.setHeight((short) 540);
    }


}
