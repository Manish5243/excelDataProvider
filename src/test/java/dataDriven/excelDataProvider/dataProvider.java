package dataDriven.excelDataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvider {

	DataFormatter formatter = new DataFormatter();

	@Test(dataProvider = "drivertest")
	public void testCaseData(String greet, String comm, String id)
	{
		System.out.println(greet+comm+id);
	}


	@DataProvider(name="drivertest")
	public Object[][] getData() throws IOException {

		//Object[][] data = {{"hello1","text1", 1},{"hello2","text2", 2},{"hello3","text3", 3}};
		//return data;

		FileInputStream fil = new FileInputStream("H:\\SDET_BY_RahulShetty\\excelDataProvider\\DataSheet.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fil);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colomn = row.getLastCellNum();

		Object data[][] = new Object[rowCount-1][colomn];

		for(int i = 0; i<rowCount-1; i++) {

			row = sheet.getRow(i+1);
			for(int j = 0; j< colomn ; j++) {

				XSSFCell cell = row.getCell(j);

				data[i][j]=formatter.formatCellValue(cell);
			}

		}
		
		return data;


	}
}
