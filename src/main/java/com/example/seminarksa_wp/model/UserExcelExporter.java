package com.example.seminarksa_wp.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<User> listUsers;

    public UserExcelExporter(List<User> listUsers) {
        this.listUsers = listUsers;
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("Users");

    }

    private void writeHeaderRow()
    {
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        Cell cell = row.createCell(0);
        cell.setCellValue("User ID");
        cell.setCellStyle(style);

         cell = row.createCell(1);
        cell.setCellValue("Name");
        cell.setCellStyle(style);

         cell = row.createCell(2);
        cell.setCellValue("Surname");
        cell.setCellStyle(style);

         cell = row.createCell(3);
        cell.setCellValue("Phone Number");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("Email");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("Country");
        cell.setCellStyle(style);

    }

    private void writeDataRows(){
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        for( User user : listUsers){
            Row row = sheet.createRow(rowCount++);

            Cell cell = row.createCell(0);
            cell.setCellValue(user.getId());
            sheet.autoSizeColumn(0);
            cell.setCellStyle(style);

             cell = row.createCell(1);
            cell.setCellValue(user.getName());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

             cell = row.createCell(2);
            cell.setCellValue(user.getSurname());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(user.getPhoneNumber());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(user.getEmail());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(user.getCountry());
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
       writeHeaderRow();
       writeDataRows();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


}
