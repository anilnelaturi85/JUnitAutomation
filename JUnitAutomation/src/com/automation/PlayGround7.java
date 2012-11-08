package com.automation;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class PlayGround7 {
	// instance variables 
	
	// methods
	public static void main (String[] args) throws Exception{
//		BufferedWriter mybufferedWriter=null;
		String constant1 = 
				"\t@Test\r\n"+
						"\tpublic void simple@@PORT_NAME@@Test() throws Exception {\r\n"+
						"\tString input = \"@@XPATH_DISPLAY@@\";\r\n"+
						
								"\t\tMap<String, OutputPortProfile> outputPorts = runParserEngineSessionWithStringAsInput(input);\r\n"+
								"\t\tString expectedValue = \"CALIBRE GROUP LTD\";\r\n"+
								"\t\tassertEquals(expectedValue, outputPorts.get(out_@@PORT_NAME@@.name()).getValue());\r\n"+
								"\t\t}";
		
		try{
//		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\c152783\\Desktop\\helloParser.java")));
//			mybufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\c152783\\Desktop\\helloParser.java")));
//			mybufferedWriter.write(constant1);
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\c152783\\Desktop\\helloParser.java")));
			writer.printf("%s",constant1);
			
			writer.close();
			
		}catch (Exception e){
			
		}
		
//		finally{
//			mybufferedWriter.close();
//		}
		
		
		}

}
