package DDTPractice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WritingDataToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\91830\\eclipse-workspace\\AdvanceSelenium_E18\\src\\test\\resources\\TestScriptData_E18.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.createSheet("WriteData").createRow(1).createCell(2).setCellValue("Selenium");
		
		FileOutputStream fos=new FileOutputStream("C:\\Users\\91830\\eclipse-workspace\\AdvanceSelenium_E18\\src\\test\\resources\\TestScriptData_E18.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("Data written successfully");

	}

}
