package com.automation;

import java.util.*;



public class Playground9 {
	// instance variables
	
	//methods
	
public String metaDataXpath(ArrayList<String> input){
		String a ="";
	for(String item:input){
		a= "<n-load id=\\\\\"683752Retro42000\\\\\">" +
				"<n-document id=\\\\\"683752Retro42000\\\\\">" +
				"<n-metadata id=\\\\\"683752Retro42000\\\\\">" +
				item+
				"</n-metadata>" +
				"</n-document>" +
				"</n-load>";
		
	}
	return a;
	}
		
	public String docBodyXpath(ArrayList<String> input){
		String a ="";
		for(String item:input){
			a= "<n-load id=\\\\\"683752Retro42000\\\\\">" +
					"<n-document id=\\\\\"683752Retro42000\\\\\">" +
					"<n-docbody id=\\\\\"683752Retro42000\\\\\">" +
					item+
					"</n-docbody>" +
					"</n-document>" +
					"</n-load>";
			
		}
		return a; 
	}
	
	
	public static void main (String[] args){
		
		String xpath="/md.merger.parties/md.target.party*/md.companyname-->/md.merger.parties/md.target.party*/md.company-->/md.merger.parties/md.target.party*/md.companyholding";
//		String xpath="/md.merger.parties/md.target.party*/md.companyname";
		int loops=1;
		Playground9 myPlayGround9 = new Playground9();
		
		
			String[] z1 = xpath.split("-->");
			for (int k = 0; k<z1.length; k++) {
			String a1 = z1[k].substring(1).trim();	
			String[] a2 = a1.split("/");
			String a3="";
			String value ="DUMMY";
			
			for (int j = (a2.length-1); j >=0; j--) {
								
				if(a2[j].indexOf("*")>1)
				{
					a3="";
					int myLoop = loops;
					while (myLoop>0) {
						a3 =a3+ "\n<"+a2[j].replace("*","")+" id=\\\\\"683752Retro42000\\\\\">"+value+"</"+a2[j].replace("*","")+">\n";
						myLoop--;
					}
					value =a3;

				} else {
					
				a3 = "\n<"+a2[j].replace("*","")+" id=\\\\\"683752Retro42000\\\\\">"+value+"</"+a2[j].replace("*","")+">\n";
					value=a3;
				}
		}
			
			ArrayList<String> stringList = new ArrayList<String>();
			stringList.add(a3);
			System.out.println("\n\rthe docbody xpath is:");
			System.out.println(myPlayGround9.docBodyXpath(stringList));
			System.out.println("\n\rthe metadata xpath is:");
			System.out.println(myPlayGround9.metaDataXpath(stringList));
//			for (String Item :stringList){
//				System.out.println("xpath is "+Item);
//				
//			}
			
			
		
	}
	}

}
