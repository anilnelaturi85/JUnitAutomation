package com.automation;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/*
 * written to test the testcase output method
 * 
 */
public class PlayGround8 {
	// instance variables
	
	// methods
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
	
	public static void main (String[] args) throws Exception{
		String inputFilename ="C:/Users/c152783/Desktop/TempTry.xls";
		String input ="DUMMY";
		
		
		File inputWorkbook = new File(inputFilename);
		Workbook w;
		try {
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet S = w.getSheet(0);
			for (int i = 1; i < S.getRows(); i++) {
				String storeFacetName = S.getCell(1, i).getContents().trim();
				String storeSaperate = S.getCell(4, i).getContents().trim();
				String storeAllTogether = S.getCell(5, i).getContents().trim();
				String storeAllTogetherSaperator = S.getCell(6, i).getContents();
				System.out.println(outputReturn("DUMMY", 1, storeSaperate, storeAllTogether, storeAllTogetherSaperator, storeFacetName));
			}
			}catch (BiffException e) {
				e.printStackTrace();
			}
		
	}

}
