package Utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {
    private Workbook workbook;
    private String filePath;

    public ExcelUtility(String filePath) throws IOException {
        this.filePath = filePath;
        FileInputStream fis = new FileInputStream(filePath);
        this.workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    public String getCellData(int sheetIndex, int rowIndex, int cellIndex) {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.getCell(cellIndex);

        if (cell == null) {
            return "";  // Return empty string if the cell is null
        }

        // Check the cell type and convert it to a string
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                double numericValue = cell.getNumericCellValue();
                if (numericValue == (long) numericValue) {
                    // If it's a whole number, treat it as long to avoid overflow
                    return String.valueOf((long) numericValue);
                } else {
                    // For non-integer values, return as double
                    return String.valueOf(numericValue);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";  // Return empty string for other cell types
        }
    }

    public void setCellData(int sheetIndex, int rowIndex, int cellIndex, String data) throws IOException {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            cell = row.createCell(cellIndex);
        }
        cell.setCellValue(data);

        // Save changes to the file
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
    }

    public void close() throws IOException {
        workbook.close();
    }
}
