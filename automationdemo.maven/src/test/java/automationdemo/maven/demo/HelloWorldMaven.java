package automationdemo.maven.demo;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HelloWorldMaven {

	@Parameters({"param1","param2"})
	@Test
	public void testHello(String text1,String text2) {
		
		System.out.println("$$$$$$$$$$$$$$$HELLO MAVEN WORLD$$$$$$$$$$$$$$"+text1+" ############### "+text2);
	}
}
