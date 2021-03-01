/**
 * 
 */
package utilities.auto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author JoshZhuang
 * use Apache POI to handle external xlsx file as input datatable
 * for Data Driven Testing
 */
public class DataManager {

	XSSFWorkbook inputworkbook; 
	XSSFSheet inputsheet;
//	String checkval;
	//construtor!!! to init datatable
	public DataManager(String excelPath,String sheetName) {
		
		try {
			File src = new File (excelPath);
			
			FileInputStream fis = new FileInputStream(src); //obtains input bytes from a file (reading)		
 
			//XSSFWorkbook - the first object most users will construct reading/writing xlsx. 
			//It is also the top level object for creating new sheets/etc.	
			inputworkbook = new XSSFWorkbook(fis); 
		
			//XSSFSheet inputsheet = wb.getSheetAt(0); //sheet object. index = 0 means first sheet i.e. Sheet1
			inputsheet = inputworkbook.getSheet(sheetName);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	//get cell value from datatable
	public String getData(int row, int column) {
		
		String data = inputsheet.getRow(row).getCell(column).getStringCellValue();
		
		return data;
		
	}
	
	
	//set cell value to datatable
	public void setData(int row, int column, String value) {
		
		inputsheet.getRow(row).getCell(column).setCellValue(value);
		
	}
	
	
	//Note assuming row 0 (first row) in datatable is always header that is not counted! 
	//So data starts from row 1 (second row)
	public int getRowCount() {
		return inputsheet.getLastRowNum();
	}
	
	
	
	//get full test data set (excluding 1st row, which is header)
	public Object[][] getTestData() {
		
		int rowindex = inputsheet.getLastRowNum()+1; // get total # of rows with data. getLastRowNum actually starts from 0, so need to +1
		int colindex = inputsheet.getRow(0).getLastCellNum(); //get # of cols 
		Object[][] data = new Object[rowindex][colindex]; // 2-dim array
		
		for (int i=0; i < (rowindex); i++) {
			for (int j=0; j<colindex; j++) {
				try {
					data[i][j] = inputsheet.getRow(i).getCell(j).toString(); // data starts from row 1 hence getRow(i+1). first row is header
				} catch (Exception e) {
					// TODO Auto-generated catch block
					data[i][j] = ""; // if that data cell is null, return ""
//					e.printStackTrace();
				}
			}		
		}
		
		return data;
	}
	
	
	
	
	//export data to xlsx workbook
	public void exportData(Object[][] data, String excelPath) throws IOException {
		int rowindex = inputsheet.getLastRowNum()+1; // get total # of rows with data. getLastRowNum actually starts from 0, so need to +1
		int colindex = inputsheet.getRow(0).getLastCellNum(); //get # of cols      
        
		for (int i=0; i < rowindex; i++) {
			Row row = inputsheet.createRow(i); //create row object for the sheet
			for (int j=0; j<colindex; j++) {
					Cell cell = row.createCell(j);  //for each row, create cells on j
					cell.setCellValue((String)data[i][j]);
				}
			}			
		
		//save to excel sheet
		FileOutputStream fos = new FileOutputStream(excelPath);
		inputworkbook.write(fos);
		inputworkbook.close();
	}
	
	
	
	
	//close workbook
	public void close(){
		try {
			inputworkbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	
}
