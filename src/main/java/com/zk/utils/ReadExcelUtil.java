package com.zk.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ReadExcelUtil {
    /**
     * read excel
     *
     * @param
     * @return
     */
    public static List<Map<String, String>> getExcuteList(String filePath) {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String, String>> list = null;
        String cellData = null;
        String columns[] = {"case", "url", "parames", "ResponseCode", "Response", "预期结果", "对比结果"};
        wb = readExcel(filePath);
        if (wb != null) {
            //用来存放表中数据
            list = new ArrayList<Map<String, String>>();
            sheet = wb.getSheetAt(0);
            int rownum = sheet.getPhysicalNumberOfRows();
            row = sheet.getRow(0);
            int colnum = row.getPhysicalNumberOfCells();

            for (int i = 1; i < rownum; i++) {
                Map<String, String> map = new LinkedHashMap<String, String>();
                row = sheet.getRow(i);
                if (row != null) {
                    for (int j = 0; j < colnum; j++) {
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                } else {
                    break;
                }
                list.add(map);
            }
        }
        return list;
    }
    public static List<Map<String, String>> getExcuteList(InputStream fileInputStream) {
        List<Map<String, String>> list;
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        list = null;
        String cellData = null;
        try {
            String columns[] = {"case", "url", "parames", "ResponseCode", "Response", "预期结果", "对比结果"};
            try {
                wb = new XSSFWorkbook(fileInputStream);
            } catch (Exception e) {
                System.err.println("Case文件不存在:\n"+e.getMessage());
            }
            if (wb != null) {
                //用来存放表中数据
                list = new ArrayList<Map<String, String>>();
                sheet = wb.getSheetAt(0);
                int rownum = sheet.getPhysicalNumberOfRows();
                row = sheet.getRow(0);
                int colnum = row.getPhysicalNumberOfCells();

                for (int i = 1; i < rownum; i++) {
                    Map<String, String> map = new LinkedHashMap<String, String>();
                    row = sheet.getRow(i);
                    if (row != null) {
                        for (int j = 0; j < colnum; j++) {
                            cellData = (String) getCellFormatValue(row.getCell(j));
                            map.put(columns[j], cellData);
                        }
                    } else {
                        break;
                    }
                    list.add(map);
                }

            }
        } finally {
            FreeIOUtils.close(fileInputStream);
            FreeIOUtils.close(wb);
        }


        return list;
    }


    /**
     * 读excel  替换参数
     * @param filePath
     * @param outfilepath
     */
    public static void  readRow(String filePath, String outfilepath){
        Workbook wb = null;
        Sheet sheet = null;
        Cell cell = null;
        wb = readExcel(filePath);
        sheet = wb.getSheetAt(0);
        /**
         * 最大行
         */
        Row row = sheet.getRow(0);
        int rownum = sheet.getPhysicalNumberOfRows();

        /**
         * 最大列
         */
        int colnum = row.getPhysicalNumberOfCells();

        System.out.println("现有case数量:" + (rownum - 1));
        for (int i = 1; i < rownum; i++) {
            for (int j = 0; j < colnum; j++) {
                row=sheet.getRow(i);
                if(j==2){
                    cell=row.getCell(2);
                    String s=getCellValue(cell);
                    row.createCell(2);
                    cell.setCellValue(replaceSign(s));
                }


            }

        }
        OutputStream out = null;
        try {
            out = new FileOutputStream(outfilepath);
            wb.write(out);
            System.out.println("生成case路径存放在:" + outfilepath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FreeIOUtils.close(out);
        }


    }

    public static String replaceSign(String str){

        String url=str.substring(str.indexOf("\"https:")+1,str.indexOf("\",\"\",{\"rotate\":true}"));
        String img="";
        String userId="duguangTB_jack_004e1a3398df4911ae846b7cae21832f";
        String type=StringUtil.getWant(str,"\"duguang_test\",\"","\",\"");
        String s=url+img+userId+type;
        String sign=MD5Utils.getMD5(s);
//

       return str.replace("duguang_test","TB_jack_004e1a3398df4911ae846b7cae21832f").replace(str.substring(str.lastIndexOf(",")+2,str.lastIndexOf("\"")),sign);
    }



    //向Excel中写数据
    public static void writeExcel(Map<String, List> map, String filePath, String outfilepath) {
        Workbook wb = null;
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        wb = readExcel(filePath);
        sheet = wb.getSheetAt(0);

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setWrapText(true);

        /**
         * 最大行
         */
        int rownum = sheet.getPhysicalNumberOfRows();
        System.out.println("现有case数量:" + (rownum - 1));
        row = sheet.getRow(0);
        /**
         * 最大列
         */
        int colnum = row.getPhysicalNumberOfCells();


        //获取列头
        Map<String, Integer> Heardmap = new HashMap<>();
        for (int i = 0; i < colnum; i++) {

            Heardmap.put(row.getCell(i).getStringCellValue(), i);

        }


        int size = 0;
        Set<String> keySet = map.keySet();
        for (String s : keySet) {
            size = map.get(s).size();
        }
        for (String heard : Heardmap.keySet()) {
            List<String> list = map.get(heard);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    row = sheet.getRow(rownum + i);
                    if (row == null) {
                        row = sheet.createRow(rownum + i);

                    }
                    //预期结果
                    Cell cell4 = row.createCell(Heardmap.get(heard));
                    cell4.setCellValue(String.valueOf(list.get(i)));
                    //自动换行
//                    cell4.setCellStyle(cellStyle);
                }

            } else {
                int caseNum = 0;
                /**
                 * 获取序列号
                 */
                try {
                    Row row1 = sheet.getRow(rownum - 1);
                    Cell cell2 = row1.getCell(0);
                    caseNum = (int) cell2.getNumericCellValue();

                } catch (Exception e) {
                    System.err.println(e);
                }
                String url = PropertiesUtils.get("casehost");
                for (int i = 0; i < size; i++) {
                    row = sheet.getRow(rownum + i);
                    if (row == null) {
                        row = sheet.createRow(rownum + i);
                    }
                    //设置用例序号自增
                    Cell cell0 = row.createCell(0);
                    caseNum++;
                    cell0.setCellValue(caseNum);
                    //设置读取url
                    Cell cell1 = row.createCell(1);
                    cell1.setCellValue(url);
                }
            }


        }
        //自动列宽
//        sheet.autoSizeColumn(5,true);
        OutputStream out = null;
        try {
            out = new FileOutputStream(outfilepath);
            wb.write(out);

            System.out.println("生成case成功,已插入" + size + "条数据,现总共case数量为:" + (rownum - 1 + size));
            System.out.println("生成case路径存放在:" + outfilepath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FreeIOUtils.close(out);
        }


    }
    public static void writeExcel2(List<String> list, String filePath, String outfilepath) {
        Workbook wb = null;
        Sheet sheet = null;
        Cell cell = null;
        wb = readExcel(filePath);
        sheet = wb.getSheetAt(0);
        /**
         * 最大行
         */
        Row row = sheet.getRow(0);
        int rownum = sheet.getPhysicalNumberOfRows();
        System.out.println("现有case数量:" + (rownum - 1));

        /**
         * 最大列
         */
        int colnum = row.getPhysicalNumberOfCells();
        int size=0;

        for (int i = 1; i < rownum; i++) {
            String s = list.get(i - 1);
            for (int j = 0; j < colnum; j++) {
                row = sheet.getRow(i);
                cell=row.getCell(5);
                if(cell==null||"".equals(getCellValue(cell))){
                    cell = row.createCell(5);
                    cell.setCellValue(s);
                    size++;
                }
            }
        }
        OutputStream out = null;
        try {
            out = new FileOutputStream(outfilepath);
            wb.write(out);

            System.out.println("生成预期结果成功,已插入" + size + "条数据,现总共case数量为:" + (rownum - 1 ));
            System.out.println("生成case路径存放在:" + outfilepath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FreeIOUtils.close(out);
        }


    }


    // 自适应宽度(中文支持)
    private static void setSizeColumn(Sheet sheet, int size) {
        for (int columnNum = 0; columnNum < size; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                Row currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }

                if (currentRow.getCell(columnNum) != null) {
                    Cell currentCell = currentRow.getCell(columnNum);
                    if (currentCell.getCellType() == CellType.STRING) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }


    /**
     * 判断excel文件的类型
     *
     * @param filePath
     * @return
     */
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {

            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = new XSSFWorkbook(is);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }


    /**
     * 获取当前列数据
     *
     * @param cell 列
     * @return 列值
     */
    private static String getCellValue(Cell cell) {
        String cellValue = "";

        if (cell == null) {
            return cellValue;
        }
        // 把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            cell.setCellType(CellType.STRING);
        }
        // 判断数据的类型
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK:
                cellValue = "";
                break;
            case ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }


}