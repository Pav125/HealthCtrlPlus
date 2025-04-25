package Utilities;

// Importing necessary classes
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    // Method to read data from an Excel file
    public Object[][] ReadData(String filePath, String sheetName) throws IOException {
        // Creating a FileInputStream to read the Excel file
        FileInputStream fis = new FileInputStream(filePath);
        
        // Using try-with-resources to ensure the workbook is closed after use
        try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            // Getting the specified sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(sheetName);
            // Getting the number of rows and cells in the sheet
           int rowCount = sheet.getLastRowNum();
            int cellCount = sheet.getRow(0).getLastCellNum();
            // Creating a 2D array to store the data
            String[][] data = new String[rowCount + 1][cellCount];
            
            // Looping through the rows and cells to read the data
            for (int i = 0; i <= rowCount; i++) {
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < cellCount; j++) {
                    XSSFCell cell = row.getCell(j);
                    // Checking if the cell is not null and storing its value in the array
                    if (cell != null) {
                        data[i][j] = cell.toString();
                    } else {
                        data[i][j] = "";
                    }
                }
            }
            // Returning the data array
            return data;
        }
    }
}
