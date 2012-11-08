package com.automation;

//import java.util.Calendar;
//import java.util.Date;

public class PlayGround3 {
	// instance variables
	
	// methods
	public static void main (String[] args){
//		Date myDate = new Date();
//		System.out.println("current timestamp is:"+myDate);
//		//myDate.
//		Calendar myCalendar = Calendar.getInstance();
//		int year = myCalendar.get(Calendar.YEAR);
//		System.out.println("current timestamp is:"+year);
//		String myString = "/merger.parties/target.company/parent.company";
//		String[] myArrayString = myString.substring(1).split("/");
//		String xPathDisplay ="";
//		String Temp="Dummy";
//		for (int i = (myArrayString.length-1); i >=0; i--) {
//			xPathDisplay = "<"+myArrayString[i]+">"+Temp+"</"+myArrayString[i]+">";
//			Temp=xPathDisplay;
//		}
//		
//		System.out.println(xPathDisplay);
		String myString = "<md.mergers>" +
				"<md.merger>" +
				"<md.transactionconditions>" +
				"<md.description*>DUMMY</md.description*>" +
				"<md.description*>DUMMY</md.description*>" +
				"<md.description*>DUMMY</md.description*>" +
				"</md.transactionconditions>" +
				"</md.merger>" +
				"</md.mergers>";
//		System.out.println(myString.indexOf("DUMMY"));
//		System.out.println(myString.lastIndexOf("DUMMY"));
//		String lastDummy = myString.substring(myString.lastIndexOf("DUMMY"), myString.lastIndexOf("DUMMY")+5);
////		System.out.println(myString.replaceFirst("DUMMY", ""));
		System.out.println(myString.substring(0,myString.indexOf("DUMMY"))+myString.substring(myString.indexOf("DUMMY")+5,myString.lastIndexOf("DUMMY"))+myString.substring(myString.lastIndexOf("DUMMY")+5));
		
	}

}
