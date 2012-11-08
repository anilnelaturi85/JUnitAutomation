package com.automation;

import java.io.File;

import jxl.*;
import jxl.read.biff.*;

public class PlayGround {
	// instance variables
	
	// methods
	
	public static void main(String[] args) throws Exception
	{
	String inputFilename ="C:/Users/c152783/Desktop/TempTry.xls";
	
	File inputWorkbook = new File(inputFilename);
	Workbook w;
	try {
		w = Workbook.getWorkbook(inputWorkbook);
		Sheet S = w.getSheet(0);
		for (int i = 1; i < S.getRows(); i++) {
//			System.out.println(S.getCell(1, i).getContents().trim());
			String myString = S.getCell(8, i).getContents().substring(1).trim();
//			String[] output1 = myString.split("-->");
			String[] output = myString.split("/");
			String Temp="DUMMY Value";
			String xPathDisplay ="";
			for (int j = (output.length-1); j >= 0; j--) {	
//				System.out.println("the output for "+ j+1 +" row is:");
//				System.out.println("<"+output[j]+">"); 
				if(output[j].indexOf("*")>1) {
				xPathDisplay = "\n<"+output[j].trim().replace("*","")+" id=\"683752Retro42000\">"+Temp+"</"+output[j].trim().replace("*","")+">\n"; // trim is used to remove accidental spaces in xpath's
				xPathDisplay = xPathDisplay+xPathDisplay; // duplicating once
//				System.out.println(xPathDisplay);
				Temp=xPathDisplay;
//				System.out.println(Temp);
				}
				else {
					xPathDisplay = "\n<"+output[j].trim()+" id=\"683752Retro42000\">"+Temp+"</"+output[j].trim()+">\n"; // trim is used to remove accidental spaces in xpath's
					Temp=xPathDisplay;
				}
			}
			System.out.println("the xpath is: ");
			System.out.println(xPathDisplay);
		}
	
	}catch (BiffException e) {
		e.printStackTrace();
	}
	
	
}
	

	
	

}
