package com.automation;

public class PrintfClass {
	// instance variables
	
	// instance methods
public static void main (String[] args){
	
	String str = "\t@Test\n"+
				"\tpublic void simple@@PORT_NAME@@Test() throws Exception {\n"+
				"\tString input = \"@@XPATH_DISPLAY@@\";\n"+
				
						"\t\tMap<String, OutputPortProfile> outputPorts = runParserEngineSessionWithStringAsInput(input);\n"+
						"\t\tString expectedValue = \"CALIBRE GROUP LTD\";\n"+
						"\t\tassertEquals(expectedValue, outputPorts.get(out_@@PORT_NAME@@.name()).getValue());\n"+
						"\t\t}";
	System.out.printf("%s",str);
		
}
	

}
