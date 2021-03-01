/**
 * 
 */
package firstpackage;

import java.io.IOException;
import java.util.Random;

import org.testng.annotations.DataProvider;

import utilities.auto.DataManager;
import utilities.auto.ToolBox;


/**
 * @author JoshZhuang
 *
 */


public class ReadDatatableDemo {

	
	static String inputdata = "testdata/dataEXP.xlsx";
	
	public static void main(String[] args) throws IOException {
//		DataManager inputsheet = new DataManager("C:\\Users\\zhuaj1\\OneDrive - SA Power Networks\\_JoshWork\\dataEXP.xlsx","Sheet1");
		DataManager inputsheet = new DataManager(inputdata,"Sheet1");
		Object data[][] = inputsheet.getTestData();
		
		
	    data[2][3] = ToolBox.getRandomNum(8);
		
		int rowNum = inputsheet.getRowCount();
		
		for (int i=1; i <= rowNum; i++) {
			String companyname = (String) data[i][0];
			String slogan = (String) data[i][1];
			String colour = (String) data[i][2];
			String logoid = (String) data[i][3];
			String price = (String) data[i][4];
			
			System.out.println(companyname+" "+slogan+" "+colour+" "+logoid+" "+price);
		}

		inputsheet.exportData(data,inputdata);
		
//		System.out.println("sthdddd");
	}
}
