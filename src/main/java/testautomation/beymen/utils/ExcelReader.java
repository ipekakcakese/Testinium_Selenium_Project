package testautomation.beymen.utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ExcelReader {

    public static String readCell(String path, int rowIndex, int colIndex) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream file = classLoader.getResourceAsStream(path);
            if (file == null) {
                throw new FileNotFoundException("File not found in classpath: " + path);
            }

            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(rowIndex);
            Cell cell = row.getCell(colIndex);
            file.close();
            return cell.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
