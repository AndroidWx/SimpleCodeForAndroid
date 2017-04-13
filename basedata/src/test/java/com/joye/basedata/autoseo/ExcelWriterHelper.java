package com.joye.basedata.autoseo;

import com.joye.basedata.ExcelUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
     *
     * @param workbook
     * @param resourceEntities
     */

    private static void writeContent(Workbook workbook, List<UploadRowResourceEntity> resourceEntities){
        Sheet sheet =workbook.createSheet("sheet1");
        //获取样式
        CellStyle mCellStyle=CreateCellStyle(workbook);
        //写入第一行
        createTitle(sheet,mCellStyle);
        //从第二行开始写入数据
        writeResourceEntityCellDataToRow(sheet,1,resourceEntities);

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
