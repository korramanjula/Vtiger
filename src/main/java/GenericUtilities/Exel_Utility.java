package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Exel_Utility {
	public String FetchDtaFromExelFile(String Sheetname,int rowindex,int cellindex) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/organization.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(Sheetname);
		Row r=sh.getRow(rowindex);
		Cell c=r.getCell(cellindex);
		String data=c.toString();
		wb.close();
		return data;
	}
	/*public void FetcMultipleDataFromExcel(String Sheetname,int rowindex,int cellindex) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/contacts.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(Sheetname);
		String data =null;
		for(int i=0;i<=sh.getLastRowNum();i++)
		{
			for(int j=0;j<=sh.getRow(i).getLastCellNum();j++) {
				
			}
		}
		}
		*/
	
public void WritebackDataTOExel(String Sheetname,int rowindex,int cellindex,String data) throws EncryptedDocumentException, IOException {
	FileInputStream fis=new FileInputStream("./src/test/resources/organization.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	Sheet sh=wb.getSheet(Sheetname);
	Row r=sh.getRow(rowindex);
	Cell c=r.getCell(cellindex);
	c.setCellValue(data);
	FileOutputStream fos=new FileOutputStream("./src/test/resources/organization.xlsx");
	wb.write(fos);
	wb.close();
	
}
	
}


