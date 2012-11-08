package com.automation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


/*
 * This class is used to generate the outputport file necessary to run the unittesting
 * Make sure the ReadExcel is ran before this is done
 */
public class OutputPort {
	// instance variables
	
	// methods
	public static void main (String[] args) throws Exception{
		Workbook w;
		String contentType ="NewsAndAlerts";
		String in_line ="";
		String Temp1 ="";
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\c152783\\workspace\\JUnitAutomation\\src\\com\\automation\\Simple_output_Template.txt"));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\c152783\\Desktop\\"+contentType.toLowerCase()+"streamdocuments\\"+contentType+"OutputPort.java")));
			while ((in_line = reader.readLine()) != null ){
				in_line = in_line.replaceAll("@@YEAR@@", ReadExcel.currYear());
				in_line = in_line.replaceAll("@@FIRSTCAPS_CONTENTTYPE@@", contentType);
				writer.println(in_line);
			}
			
			try{
				w = Workbook.getWorkbook(new File("C:/Users/c152783/Desktop/SecondarySources-NewsAndAlerts.xls"));
				Sheet s = w.getSheet(0);
				for (int i = 1; i < s.getRows(); i++) {
					String storeFacetName = s.getCell(1, i).getContents().trim();
					String storeFacetTemp="";
					String[] output1 = storeFacetName.split("\\.");
					for (int j = 0; j < output1.length; j++) {	
						storeFacetTemp = storeFacetTemp+ReadExcel.capitalize(output1[j]);
					}
				Temp1 = Temp1+"\tout_"+storeFacetTemp+",\r\n";
				}
				
			}catch (BiffException e) {
				e.printStackTrace();
			}
			
		Temp1=Temp1.replaceAll(",$", ";");
//		writer.printf("%s", "\r\n\r\n"+Temp1+"\r\n");
		writer.printf("%s", Temp1+"\r\n");
		writer.printf("%s","\r\n\tprivate "+contentType+"OutputPort() {\r\n\t}\r\n}");
		reader.close();
		writer.close();		
			
		}catch (Exception e){
			
		}
		
System.out.println("Output file has been created!");
	}
}
