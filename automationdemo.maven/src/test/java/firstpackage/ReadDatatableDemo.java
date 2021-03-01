/**
 * 
 */
package firstpackage;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utilities.auto.DataManager;


/**
 * @author JoshZhuang
 *
 */




public class ReadDatatableDemo {

	
	
	public static void main(String[] args) throws IOException {
		DataManager inputsheet = new DataManager("C:\\Users\\zhuaj1\\OneDrive - SA Power Networks\\_JoshWork\\dataEXP.xlsx","Sheet1");
		
		Object data[][] = inputsheet.getTestData();
		
		data[3][3] = "new---logo";
		data[1][4] = "255";
		
//		inputsheet.exportData("testdata/dataEXP.xlsx");
		inputsheet.exportData(data,"C:\\Users\\zhuaj1\\OneDrive - SA Power Networks\\_JoshWork\\dataEXP.xlsx");
		
		System.out.println("sthdddd");
	}
}
