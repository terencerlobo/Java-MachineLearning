package excelReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	
	public static List<double[]> readExcel(String filePath) throws Exception {
        FileInputStream file = new FileInputStream(new File(filePath));

        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);
        List<double[]> data = new ArrayList<>();

        for (Row row : sheet) {
            double[] rowData = new double[3]; 
            int i = 0;
            for (Cell cell : row) {
                rowData[i++] = cell.getNumericCellValue();
            }
            data.add(rowData);
        }

        workbook.close();
        return data;
    }

}
