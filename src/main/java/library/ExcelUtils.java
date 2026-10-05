package library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	private static Workbook workbook;
	private static Sheet sheet;
	private static String file_path = System.getProperty("user.dir")+"/src/test/resources/TestData.xlsx";
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(file_path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			workbook = WorkbookFactory.create(fin);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sheet = workbook.getSheet(sheetName);
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		DataFormatter formatData = new DataFormatter();
		
		for(int i=0; i<sheet.getLastRowNum(); i++) {
			for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = formatData.formatCellValue(sheet.getRow(i+1).getCell(j));
			}
		}
		
		return data;
	}

}
