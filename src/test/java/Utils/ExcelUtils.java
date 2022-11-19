package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtils {

    private static Workbook excelBook;
    private static Sheet excelSheet;
    private static Row row;
    private static Cell cell;
    private static String filePath;

    public static void openExcelFile(String fileName, String sheetName){
        filePath = "src/test/resources/com.cucumber.features.ExcelData/"+fileName+".xlsx";
        try {
            File file = new File(filePath);
            FileInputStream inputStream =  new FileInputStream(file);
            excelBook = new XSSFWorkbook(inputStream);
            excelSheet = excelBook.getSheet(sheetName);

        }catch (Exception e){
            System.out.println("The file does not exist");
        }

    }

    public static String getCellValue(int rowNumber, int cellNumber){
        rowNumber = rowNumber-1;
        cellNumber = cellNumber-1;
        row = excelSheet.getRow(rowNumber);
        cell = row.getCell(cellNumber);
        return cell.toString();
    }

    public static void setCellValue(int rowNumber, int cellNumber,String value){

        rowNumber = rowNumber-1;
        cellNumber = cellNumber-1;
        row = excelSheet.getRow(rowNumber);
        cell = row.getCell(cellNumber);
        if(cell == null){
            row = excelSheet.createRow(rowNumber);
            cell = row.createCell(cellNumber);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(value);
        }else{
            cell.setCellValue(value);
        }

    }

    public static void printAllValues(){
        int rowNumber = excelSheet.getLastRowNum()-excelSheet.getFirstRowNum();

        for(int i=excelSheet.getFirstRowNum();i<=excelSheet.getLastRowNum();i++){

            Row row = excelSheet.getRow(i);
            System.out.print("Row: "+i+" ");

            for(int j=row.getFirstCellNum();j<row.getLastCellNum();j++){
                Cell cell = row.getCell(j);
                System.out.print("Cell:"+j+" "+cell+" ");
            }
            System.out.println();
        }

    }

    public static void closeWorkbook()  {
        try {
            excelBook.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
