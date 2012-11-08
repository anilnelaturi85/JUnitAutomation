package com.automation;
import jxl.*;
import java.io.*;;
/*
 * wrote this program to see how to extract data from excel and which function to use for comparing it
 * with a real value on a if loop
 * 
 */
public class PlayGround4 {
	// instance variables
	
	// methods
	public static void main(String[] args) throws Exception{
		
		Workbook workbook = Workbook.getWorkbook(new File("C:\\Users\\c152783\\Desktop\\TempTry.xls"));
		Sheet newSheet = workbook.getSheet(0);
		for (int i = 1; i < newSheet.getRows(); i++) {
			String facetFlag = newSheet.getCell(0, i).getContents(); 
			System.out.println(facetFlag);
			if ("Y".equalsIgnoreCase(facetFlag)) {
				System.out.println("Found a facet!");
				
			}else {
				System.out.println("No facet found!");
			}
		}
		
	}

}
