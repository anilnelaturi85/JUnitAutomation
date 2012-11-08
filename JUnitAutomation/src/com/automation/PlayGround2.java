package com.automation;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


public class PlayGround2 {
	// instance variables
		
	// methods
	public static void main(String[] args) throws Exception{
//		FileReader myFileReader = null;
//		FileWriter myFileWriter = null;
//		BufferedReader myReaderBuffer = null;
//		BufferedWriter myWriterBuffer = null;
		String in_line = "";
		String contentType="MergerAcqns";
		/*
		try{
		
			myFileReader = new FileReader("C:\\Users\\c152783\\workspace\\JUnitAutomation\\src\\com\\automation\\Simple_Parser_Template.txt");
			myReaderBuffer = new BufferedReader(myFileReader);
			System.out.println("opened the reader file ...");
			myFileWriter = new FileWriter("C:/Users/c152783/Desktop/sample.txt");
			myWriterBuffer = new BufferedWriter(myFileWriter);
			System.out.println("opened the Writer file ...");
			
			while ((in_line=myReaderBuffer.readLine())!= null ){
				in_line = in_line.replaceAll("@@ALL_LOWER_CONTENTTYPE@@", contentType.toLowerCase());
				myWriterBuffer.write(in_line);
			}
		}finally{
			myReaderBuffer.close();
			myWriterBuffer.close();
			System.out.println("output written");
		}
		*/
		BufferedReader reader = null;
		PrintWriter writer = null;
				
		try{
			reader = new BufferedReader(new FileReader("C:\\Users\\c152783\\workspace\\JUnitAutomation\\src\\com\\automation\\Simple_Parser_Template.txt"));
			writer = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/c152783/Desktop/sample.txt")));
			while ((in_line = reader.readLine()) != null ){
				in_line = in_line.replaceAll("@@ALL_LOWER_CONTENTTYPE@@", contentType.toLowerCase());
				writer.println(in_line);
			}
		}finally{
			reader.close();
			writer.close();
		}
		
		
	
	}
		
}
