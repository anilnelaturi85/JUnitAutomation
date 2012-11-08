package com.automation;

import java.io.*;
import jxl.*;



public class PlayGround5 {
	// instance variables
	
	
	// methods
	
	public String xPathReturn(String xpath, int loops, String value){
		String a1 = xpath.substring(1).trim();	
		String[] a2 = a1.split("/");
		String a3="";
		String temp="";
		
		for (int j = (a2.length-1); j >=0; j--) {
			
			if(a2[j].indexOf("*")>1)
			{
				a3="";
				int myLoop = loops;
				while (myLoop>0) {
					temp = "\n<"+a2[j]+">"+value+"</"+a2[j]+">\n";
					a3 =a3+temp;
					myLoop--;
				}
				value =a3;
//				System.out.println(a3);
			} else {
				
				a3 = "\n<"+a2[j]+">"+value+"</"+a2[j]+">\n";
				value=a3;
//			a3 = "<"+a2[j]+">"+value+"</"+a2[j]+">";
//			value =a3;
			}
		}
		
//		return "\""+temp+"\"";
		return "\""+a3+"\"";
	}
	
	public String valueReplace(String xPath, String input){
		String myXpath = xPath;
		String myInput = input;
		return myXpath.substring(0,myXpath.indexOf(myInput))+myXpath.substring(myXpath.indexOf(myInput)+myInput.length(),myXpath.lastIndexOf(myInput))+myXpath.substring(myXpath.lastIndexOf(myInput)+myInput.length());
		
		}
	
	public static void main(String[] args) throws Exception{
		
//		String string1 = args[0];
//		String string2 = args[1];
//		System.out.println(string1+" and "+string2);
		
//		String temp1 = "Dummy";
//		String a3="";
		
		PlayGround5 myPlayGroung = new PlayGround5();
		Workbook workbook = Workbook.getWorkbook(new File("C:\\Users\\c152783\\Desktop\\TempTry.xls"));
		Sheet newSheet = workbook.getSheet(0);
		for (int i = 1; i < newSheet.getRows(); i++) {
			String facetFlag= newSheet.getCell(0, i).getContents();
			System.out.println(facetFlag);
			String elementName= newSheet.getCell(1, i).getContents();
			String storeSaperate= newSheet.getCell(4, i).getContents();
			String storeAllTogether= newSheet.getCell(5, i).getContents();
			String saperator= newSheet.getCell(6, i).getContents();
			String xPath= newSheet.getCell(8, i).getContents();
			System.out.println("Simple test case:");
			System.out.println(myPlayGroung.xPathReturn(xPath, 1, "DUMMY"));
			if(xPath.indexOf("*")>1){
			System.out.println("multiple test case:");
			System.out.println(myPlayGroung.xPathReturn(xPath, 2, "DUMMY"));
			System.out.println("First and last null test case:");
			System.out.println(myPlayGroung.valueReplace(myPlayGroung.xPathReturn(xPath, 3, "DUMMY"), "DUMMY"));
			}
			if("Y".equalsIgnoreCase(facetFlag)){
				System.out.println("_256 test case:");
				System.out.println(myPlayGroung.xPathReturn(xPath, 1, "_256CHARS"));
				System.out.println("em space test case:");
				System.out.println(myPlayGroung.xPathReturn(xPath, 1, "em_space"));
				System.out.println("en space test case:");
				System.out.println(myPlayGroung.xPathReturn(xPath, 1, "en_space"));
				
			}
			else{
				System.out.println("not facetted!");
			}
//			a1 = a1.substring(1).trim();
//			String[] a2 = a1.split("/");
//			for (int j = (a2.length-1); j >=0; j--) {
//				a3 = "<"+a2[j]+">"+temp1+"</"+a2[j]+">";
//				temp1 =a3;
//				
//			}
//			System.out.println("\""+a3+"\"");
//			//			System.out.println("the value of the cell 0X"+ i +" from the excel is: "+a1);	
		}
		
	}

}