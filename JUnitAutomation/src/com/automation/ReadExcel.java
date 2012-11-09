package com.automation;

/*
 * For a new content change the contenttype value below and the value of the file
 * in the main method
 * 
 * 
 */

import java.io.File;


import jxl.*;
import jxl.read.biff.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;


public class ReadExcel {
	// instance variables
	public String inputFile ="";
	public String contentType ="NewsAndAlerts"; 
	
	
	//methods
	
		// constructor
	public ReadExcel(){
			
	}

public void read() throws Exception {
		
		String constant1 = 
				"\t@Test\r\n"+
						"\tpublic void @@TESTNAME@@@@PORT_NAME@@Test() throws Exception {\r\n"+
						"\tString input = \"@@XPATH_DISPLAY@@\";\r\n"+
						
								"\t\tMap<String, OutputPortProfile> outputPorts = runParserEngineSessionWithStringAsInput(input);\r\n"+
								"\t\tString expectedValue = \"@@TESTOUT@@\";\r\n"+
								"\t\tassertEquals(expectedValue, outputPorts.get(out_@@PORT_NAME@@.name()).getValue());\r\n"+
								"\t}\r\n";
		File inputWorkbook = new File(inputFile);
		File newDir = new File("C:/Users/c152783/Desktop/"+ contentType.toLowerCase()+"streamdocuments");
		newDir.mkdir();	
		String in_line =""; 
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet S = w.getSheet("NFieldsInfo");
			System.out.println("No of testfiles created are: "+S.getRows());
			for (int i = 1; i < S.getRows(); i++) {
//				System.out.println(S.getCell(1, i).getContents().trim());
				String storeFacetNeeded = S.getCell(0, i).getContents().trim(); // to store if this particular field is a facet
				String storeFacetName = S.getCell(1, i).getContents().trim(); // to store the facet field information
				String storeDocBody  = S.getCell(2, i).getContents().trim(); // to store the docbody field value and use for later
				String storeSaperate = S.getCell(4, i).getContents().trim();
				String storeAllTogether = S.getCell(5, i).getContents().trim();
				String storeAllTogetherSaperator = S.getCell(6, i).getContents();
				String storeXPath  = S.getCell(8, i).getContents().trim(); // to store the xpath field information
				
				// splitting the facet name based on . and then captalizing the first character and then storing it for later use
				String storeFacetTemp="";
				String[] output1 = storeFacetName.split("\\.");
				for (int j = 0; j < output1.length; j++) {	
					storeFacetTemp = storeFacetTemp+capitalize(output1[j]); 
					
				}
				
//				//Splitting the Xpath value using a / and then saving it for further use
//				String[] output2 = storeXPath.split("/");
//				String storeXPathTemp="DUMMY Value";
//				String xPathDisplay ="";
//				for (int k = (output2.length-1); k >= 0; k--) {	
////					System.out.println("the output for "+ j+1 +" row is:");
//					String replacestar = output2[k]; 
////					System.out.println("<"+output[j]+">"); 
//					if(output2[k].indexOf("*")>1) {
//					xPathDisplay = "\n<"+replacestar.trim().replace("*","")+" id=\"683752Retro42000\">"+storeXPathTemp+"</"+replacestar.trim().replace("*","")+">\n"; // trim is used to remove accidental spaces in xpath's
//					xPathDisplay = xPathDisplay+xPathDisplay;
//					storeXPathTemp=xPathDisplay;
//					} else {
//						xPathDisplay = "\n<"+replacestar.trim().replace("*","")+" id=\"683752Retro42000\">"+storeXPathTemp+"</"+replacestar.trim().replace("*","")+">\n"; // trim is used to remove accidental spaces in xpath's
//						storeXPathTemp=xPathDisplay;
//					}
//					
//					
//				}
////				System.out.println(xPathDisplay);
			
				
				
				
				// this try and catch block is a file writer which generates outputfiles
				try{
					
					//myFileWriter = new FileWriter(newDir.getPath()+"\\"+contentType+storeFacetTemp+"Parser.java");
					//myFileBuffer = new BufferedWriter(myFileWriter);
					BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\c152783\\git\\JUnitAutomation\\JUnitAutomation\\src\\com\\automation\\Simple_Parser_Template.txt"));
					PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(newDir.getPath()+"\\"+contentType+storeFacetTemp+"Parser.java")));
					while ((in_line = reader.readLine()) != null ){
						in_line = in_line.replaceAll("@@ALL_LOWER_CONTENTTYPE@@", contentType.toLowerCase()); // sets the foldername
						in_line = in_line.replaceAll("@@MD_or_DB_ALL_LOWER_CONTENTTYPE@@", ("Y".equalsIgnoreCase(storeDocBody))?contentType.toLowerCase()+"_db":contentType.toLowerCase()+"_md"); // sets the servicename
						in_line = in_line.replaceAll("@@YEAR@@", currYear()); // sets the copyright to the current year
						in_line = in_line.replaceAll("@@PORT_NAME@@", storeFacetTemp); // replaces the port name with the current facetname
						in_line = in_line.replaceAll("@@FIRSTCAPS_CONTENTTYPE@@", contentType); // replace parser with the current content name
						in_line = in_line.replaceAll("@@FACET_REPLACE_DOT@@", storeFacetName.replace(".", "_")); // assigns the right service name
												
						writer.println(in_line);
						
					}
					// if an or exists in the xpath then for each xpath it shld create all the following scenarios
					String[] z1 = storeXPath.split("-->");
					for (int k = 0; k<z1.length; k++)
					{
//						System.out.println("the xpath is "+z1[k]); // used for debugging the split xpaths
					if("Y".equalsIgnoreCase(storeDocBody)){
						// simple testcase
						writer.printf("%s",
								constant1.
								replaceAll("@@TESTNAME@@", z1.length>1?"Simple"+(k+1):"Simple").
								replaceAll("@@PORT_NAME@@", storeFacetTemp).
								replaceAll("@@XPATH_DISPLAY@@", docBodyXpath(xPathReturn(z1[k], 1, "DUMMY"))).
								replaceAll("@@TESTOUT@@", outputReturn("DUMMY", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
						// no value test case
						writer.printf("%s",
								constant1.
								replaceAll("@@TESTNAME@@", z1.length>1?"missingValue"+(k+1):"missingValue").
								replaceAll("@@PORT_NAME@@", storeFacetTemp).
								replaceAll("@@XPATH_DISPLAY@@", docBodyXpath(xPathReturn(z1[k], 1, ""))).
								replaceAll("@@TESTOUT@@", outputReturn("", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
						
						// missing tags test case
						writer.printf("%s",
								constant1.
								replaceAll("@@TESTNAME@@", z1.length>1?"missingTags"+(k+1):"missingTags").
								replaceAll("@@PORT_NAME@@", storeFacetTemp).
								replaceAll("@@XPATH_DISPLAY@@", docBodyXpath(xPathReturn("", 1, ""))).
								replaceAll("@@TESTOUT@@", outputReturn("", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
						
						if(z1[k].indexOf("*")>1){
							// multiple repeating groups test case
							writer.printf("%s",
									constant1.
									replaceAll("@@TESTNAME@@", z1.length>1?"multiple"+(k+1):"multiple").
									replaceAll("@@PORT_NAME@@", storeFacetTemp).
									replaceAll("@@XPATH_DISPLAY@@", docBodyXpath(xPathReturn(z1[k], 2, "DUMMY"))).
									replaceAll("@@TESTOUT@@", outputReturn("DUMMY", 2, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));	
							// multiple repeating groups with nulls
							writer.printf("%s",
									constant1.
									replaceAll("@@TESTNAME@@", z1.length>1?"multipleNulls"+(k+1):"multipleNulls").
									replaceAll("@@PORT_NAME@@", storeFacetTemp).
									replaceAll("@@XPATH_DISPLAY@@", valueReplace(docBodyXpath(xPathReturn(z1[k], 3, "DUMMY")), "DUMMY")).
									replaceAll("@@TESTOUT@@", outputReturn("DUMMY", 3, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
						}
						if("Y".equalsIgnoreCase(storeFacetNeeded)){
							
							// 255 chars testcase
							writer.printf("%s",
									constant1.
									replaceAll("@@TESTNAME@@", z1.length>1?"_256CHARS"+(k+1):"_256CHARS").
									replaceAll("@@PORT_NAME@@", storeFacetTemp).
									replaceAll("@@XPATH_DISPLAY@@", docBodyXpath(xPathReturn(z1[k], 1, "here is a string that is 256 characters long.  here is a string that is 256 characters long.  here is a string that is 256 characters long.  here is a string that is 256 characters long.  To test this the 255th character is a X, 256th character is a 6 SIX6"))).
									replaceAll("@@TESTOUT@@", outputReturn("here is a string that is 256 characters long.  here is a string that is 256 characters long.  here is a string that is 256 characters long.  here is a string that is 256 characters long.  To test this the 255th character is a X, 256th character is a 6", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
							// em space
							writer.printf("%s",
									constant1.replaceAll("@@TESTNAME@@", z1.length>1?"emSpace"+(k+1):"emSpace").
									replaceAll("@@PORT_NAME@@", storeFacetTemp).
									replaceAll("@@XPATH_DISPLAY@@", docBodyXpath(xPathReturn(z1[k], 1, " Em Space "))).
									replaceAll("@@TESTOUT@@", outputReturn(" Em Space ", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
							// en space
							writer.printf("%s",
									constant1.replaceAll("@@TESTNAME@@", z1.length>1?"enSpace"+(k+1):"enSpace").
									replaceAll("@@PORT_NAME@@", storeFacetTemp).replaceAll("@@XPATH_DISPLAY@@", docBodyXpath(xPathReturn(z1[k], 1, " En Space "))).
									replaceAll("@@TESTOUT@@", outputReturn(" En Space ", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
						}
						// linefeeds and tags
						writer.printf("%s",
								constant1.replaceAll("@@TESTNAME@@", z1.length>1?"Tags_LineFeed_MarginSpace"+(k+1):"Tags_LineFeed_MarginSpace").
								replaceAll("@@PORT_NAME@@", storeFacetTemp).
								replaceAll("@@XPATH_DISPLAY@@", docBodyXpath(xPathReturn(z1[k], 1, "     <bop></bop> DU\\\\n\\\\rMMY    </bos> "))).
								replaceAll("@@TESTOUT@@", outputReturn("DUMMY", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
						writer.printf("%s", "}");
					} else {
						// simple testcase
						writer.printf("%s",
								constant1.replaceAll("@@TESTNAME@@", z1.length>1?"Simple"+(k+1):"Simple").
								replaceAll("@@PORT_NAME@@", storeFacetTemp).
								replaceAll("@@XPATH_DISPLAY@@", metaDataXpath(xPathReturn(z1[k], 1, "DUMMY"))).
								replaceAll("@@TESTOUT@@", outputReturn("DUMMY", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
						// missing value testcase
						writer.printf("%s",
								constant1.replaceAll("@@TESTNAME@@", z1.length>1?"missingValue"+(k+1):"missingValue").
								replaceAll("@@PORT_NAME@@", storeFacetTemp).
								replaceAll("@@XPATH_DISPLAY@@", metaDataXpath(xPathReturn(z1[k], 1, ""))).
								replaceAll("@@TESTOUT@@", outputReturn("", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
						
						// missing tags test case
						writer.printf("%s",
								constant1.
								replaceAll("@@TESTNAME@@", z1.length>1?"missingTags"+(k+1):"missingTags").
								replaceAll("@@PORT_NAME@@", storeFacetTemp).
								replaceAll("@@XPATH_DISPLAY@@", metaDataXpath(xPathReturn("", 1, ""))).
								replaceAll("@@TESTOUT@@", outputReturn("", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
						
						if(z1[k].indexOf("*")>1){
							// multiple repeating group test case
							writer.printf("%s",
									constant1.replaceAll("@@TESTNAME@@", z1.length>1?"multiple"+(k+1):"multiple").
									replaceAll("@@PORT_NAME@@", storeFacetTemp).
									replaceAll("@@XPATH_DISPLAY@@", metaDataXpath(xPathReturn(z1[k], 2, "DUMMY"))).
									replaceAll("@@TESTOUT@@", outputReturn("DUMMY", 2, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
							// multiple repeating group test case with nulls
							writer.printf("%s",
									constant1.replaceAll("@@TESTNAME@@", z1.length>1?"multipleNulls"+(k+1):"multipleNulls").
									replaceAll("@@PORT_NAME@@", storeFacetTemp).
									replaceAll("@@XPATH_DISPLAY@@", valueReplace(metaDataXpath(xPathReturn(z1[k], 3, "DUMMY")), "DUMMY")).
									replaceAll("@@TESTOUT@@", outputReturn("DUMMY", 3, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
						}
						if("Y".equalsIgnoreCase(storeFacetNeeded)){
							// 255 chars testcase
							writer.printf("%s",
									constant1.replaceAll("@@TESTNAME@@", z1.length>1?"_256CHARS"+(k+1):"_256CHARS").
									replaceAll("@@PORT_NAME@@", storeFacetTemp).
									replaceAll("@@XPATH_DISPLAY@@", metaDataXpath(xPathReturn(z1[k], 1, "here is a string that is 256 characters long.  here is a string that is 256 characters long.  here is a string that is 256 characters long.  here is a string that is 256 characters long.  To test this the 255th character is a X, 256th character is a 6 SIX6"))).
									replaceAll("@@TESTOUT@@", outputReturn("here is a string that is 256 characters long.  here is a string that is 256 characters long.  here is a string that is 256 characters long.  here is a string that is 256 characters long.  To test this the 255th character is a X, 256th character is a 6", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
							// em space
							writer.printf("%s",
									constant1.replaceAll("@@TESTNAME@@", z1.length>1?"emSpace"+(k+1):"emSpace").
									replaceAll("@@PORT_NAME@@", storeFacetTemp).
									replaceAll("@@XPATH_DISPLAY@@", metaDataXpath(xPathReturn(z1[k], 1, " Em Space "))).
									replaceAll("@@TESTOUT@@", outputReturn(" Em Space ", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
							// en space
							writer.printf("%s",
									constant1.replaceAll("@@TESTNAME@@", z1.length>1?"enSpace"+(k+1):"enSpace").
									replaceAll("@@PORT_NAME@@", storeFacetTemp).
									replaceAll("@@XPATH_DISPLAY@@", metaDataXpath(xPathReturn(z1[k], 1, " En Space "))).
									replaceAll("@@TESTOUT@@", outputReturn(" En Space ", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
						}
						writer.printf("%s",
								constant1.replaceAll("@@TESTNAME@@", z1.length>1?"Tags_LineFeed_MarginSpace"+(k+1):"Tags_LineFeed_MarginSpace").
								replaceAll("@@PORT_NAME@@", storeFacetTemp).
								replaceAll("@@XPATH_DISPLAY@@", metaDataXpath(xPathReturn(z1[k], 1, "     <bop></bop> DU\\\\n\\\\rMMY    </bos> "))).
								replaceAll("@@TESTOUT@@", outputReturn("DUMMY", 2, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName)));
						writer.printf("%s", "}");
					}
					}
					
//					writer.printf("%s",constant1.replaceAll("@@PORT_NAME@@", storeFacetTemp));
//					writer.print("\n\t@Test\npublic void simple" +storeFacetTemp+"Test() throws Exception {\n\t\tString input = \""+"\""+";");
//					myFileBuffer.write("this line is written!");
					reader.close();
					writer.close();
					
				} catch (Exception e){
					
				}
				
				System.out.println("Created Junit TestCase "+contentType+storeFacetTemp+"Parser.java");
			}
			
		} catch (BiffException e) {
			e.printStackTrace();
		}
		
		
	}

// static method for capitalizing the first letter of each input word 	
	public static String capitalize(String inWord)
	 {
		 if (inWord == null || inWord.trim().length() == 0) 
		 	{
			 return "";
			 }
		 if (inWord.trim().length() == 1) 
		 	{return inWord.toUpperCase();
		 	}
		 
	     return Character.toUpperCase(inWord.charAt(0)) + inWord.substring(1);
	 }
	
	public static String currYear(){
		
		Date date = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
		String year = simpleDateformat.format(date);
		return year;
	}
	
	public static String metaDataXpath(String input){
		
		return "<n-load id=\\\\\"683752Retro42000\\\\\">" +
				"<n-document id=\\\\\"683752Retro42000\\\\\">" +
				"<n-metadata id=\\\\\"683752Retro42000\\\\\">" +
				input+
				"</n-metadata>" +
				"</n-document>" +
				"</n-load>";
	}
		
	public static String docBodyXpath(String input){
		
		return "<n-load id=\\\\\"683752Retro42000\\\\\">" +
				"<n-document id=\\\\\"683752Retro42000\\\\\">" +
				"<n-docbody id=\\\\\"683752Retro42000\\\\\">" +
				input+
				"</n-docbody>" +
				"</n-document>" +
				"</n-load>";
	}
	
	
	
	public static String xPathReturn(String xpath, int loops, String value){
		String a3="";
		
		if(!xpath.isEmpty()){ 
		String a1 = xpath.substring(1).trim();	
		String[] a2 = a1.split("/");
			
		
		for (int j = (a2.length-1); j >=0; j--) {
			
			if(a2[j].indexOf("*")>1)
			{
				a3="";
				int myLoop = loops;
				while (myLoop>0) {
//					temp = "\n<"+a2[j]+" id=\\\\\"683752Retro42000\\\\\">"+value+"</"+a2[j]+">\n";
					a3 =a3+ "<"+a2[j].replace("*","")+" id=\\\\\"683752Retro42000\\\\\">"+value+"</"+a2[j].replace("*","")+">";
					myLoop--;
				}
				value =a3;
//				System.out.println(a3);
			} else {
				
//				a3 = "\n<"+a2[j]+" id=\\\\\"683752Retro42000\\\\\">"+value+"</"+a2[j]+">\n";
				a3 = "<"+a2[j].replace("*","")+" id=\\\\\"683752Retro42000\\\\\">"+value+"</"+a2[j].replace("*","")+">";
				value=a3;
//			a3 = "<"+a2[j]+">"+value+"</"+a2[j]+">";
//			value =a3;
			}
		}
		
//		return "\""+temp+"\"";
		//"\""+a3+"\""
		} else {
			a3="";
		}
		return a3;
	}
	
	public static String valueReplace(String xPath, String input){
		String myXpath = xPath;
		String myInput = input;
		return myXpath.substring(0,myXpath.indexOf(myInput))+myXpath.substring(myXpath.indexOf(myInput)+myInput.length(),myXpath.lastIndexOf(myInput))+myXpath.substring(myXpath.lastIndexOf(myInput)+myInput.length());
		
		}
	
	public static String outputReturn(String input, int loops, String storeSaperate, String storeAllTogether, String storeAllTogetherSaperator, String storeFacetName){
		int myLoops = loops;
		String temp="";
		while(myLoops>0){
			if("Y".equalsIgnoreCase(storeSaperate)){
				temp = temp+storeFacetName+":"+input+"|*|";
				
			} else if("Y".equalsIgnoreCase(storeAllTogether)){
				temp = temp+input+storeAllTogetherSaperator;
				
			}else {
				
				temp = input;
			}
			
			
			myLoops--;
		}
		return temp.replaceAll(storeAllTogetherSaperator+"$","" );	
	}
		
	public static void main(String[] args) throws Exception{
		ReadExcel myExcel = new ReadExcel();
		myExcel.inputFile="C:/Users/c152783/Desktop/SecondarySources-NewsAndAlerts.xls";
		myExcel.read();
		
	}
	

}

