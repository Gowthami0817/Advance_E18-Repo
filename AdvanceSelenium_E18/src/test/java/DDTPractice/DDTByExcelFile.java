package DDTPractice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DDTByExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\91830\\eclipse-workspace\\AdvanceSelenium_E18\\src\\test\\resources\\TestScriptData_E18.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String Campaign = wb.getSheet("DDTTestcase_ID").getRow(1).getCell(2).getStringCellValue();
		String targetSize= wb.getSheet("DDTTestcase_ID").getRow(1).getCell(3).getStringCellValue();
		//Sheet sh = wb.getSheet("DDTTestcase_ID");
		//Row row = sh.getRow(1);
		//Cell cell = row.getCell(2);
		//String Campaign = cell.getStringCellValue();
		System.out.println(Campaign);
		System.out.println(targetSize);
		
		
		
	}

}
