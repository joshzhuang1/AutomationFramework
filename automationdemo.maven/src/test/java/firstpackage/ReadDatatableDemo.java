/**
 * 
 */
package firstpackage;

import org.testng.annotations.DataProvider;

import utilities.auto.ExcelDataConfig;

/**
 * @author JoshZhuang
 *
 */




public class ReadDatatableDemo {

	
	
	public static void main(String[] args) {
		ExcelDataConfig inputsheet = new ExcelDataConfig("datatable/CalcInput.xlsx","Sheet1");
		int rowcount = inputsheet.getRowCount();
		
		for (int i=1; i<=rowcount; i++) {
			System.out.println(inputsheet.getData(i, 0));
			System.out.println(inputsheet.getData(i, 1));
			System.out.println(inputsheet.getData(i, 2));
			System.out.println(inputsheet.getData(i, 3));
		}
		
		inputsheet.close();
	}
}
