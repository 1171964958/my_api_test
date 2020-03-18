package com.zk.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POIExcelUtil {
    public static void readXls(String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);

        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }

            // 循环行Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }

                // 循环列Cell
                for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
                    XSSFCell hssfCell = hssfRow.getCell(cellNum);
                    if (hssfCell == null) {
                        continue;
                    }

                    System.out.print("    " + getValue(hssfCell));
                }
                System.out.println();
            }
        }
    }

    public static String getValue(XSSFCell hssfCell) {
        if (hssfCell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    //xlsx
    public static void readXlsx() throws IOException {
        String fileName = "D:\\excel\\xlsx_test.xlsx";
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileName);

        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }

            // 循环行Row
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }

                // 循环列Cell
                for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                    XSSFCell xssfCell = xssfRow.getCell(cellNum);
                    if (xssfCell == null) {
                        continue;
                    }
                    System.out.print("   " + getsValue(xssfCell));
                }
                System.out.println();
            }
        }
    }

    public static String getsValue(XSSFCell xssfCell) {
        if (xssfCell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(xssfCell.getNumericCellValue());
        } else {
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }

    public static void main(String[] args) throws IOException {
//        readXls("D:\\ideaProject\\ht_wordFile\\src\\main\\resources\\case.xlsx");
        Map<String, List> map = new HashMap<>();
        List<String> list = new ArrayList();
        List<String> list1 = new ArrayList();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");
        map.put("预期结果", list);
        list1.add("95");
        list1.add("96");
        list1.add("97");
        list1.add("98");
        list1.add("99");

//        map.put("Case", list1);
//        readXls("D:\\IdeaProjects\\my_api_test\\src\\main\\resources\\config\\case1.xlsx");
////        ReadExcelUtil.writeExcel(map, "D:\\ideaProject\\ht_wordFile\\src\\main\\resources\\case.xlsx", "D:\\ideaProject\\ht_wordFile\\src\\main\\resources\\case1.xlsx");

        String CasePath = PropertiesUtils.get("casePath");
        List<Map<String, String>> result = ReadExcelUtil.getExcuteList(CasePath);
        Object[][] files = new Object[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            files[i] = new Object[]{result.get(i)};
        }

    }

}