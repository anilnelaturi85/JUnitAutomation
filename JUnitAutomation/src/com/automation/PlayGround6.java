package com.automation;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class PlayGround6 {
	 public static void main(String[] args){
	        
		 		System.out.println(args[0]);
		 		System.out.println(args[1]);
		 		Pattern pattern = 
	            Pattern.compile(args[0]); // enter the input xpath here

	            Matcher matcher = 
	            pattern.matcher(args[1]); // enter the search string here

	            boolean found = false;
	            while (matcher.find()) {
	                System.out.printf("I found the text" +
	                    " \"%s\" starting at " +
	                    "index %d and ending at index %d.%n",
	                    matcher.group(),
	                    matcher.start(),
	                    matcher.end());
	                found = true;
	            }
	            if(!found){
	            	System.out.println("No match found");
	            }
	        }
	    

}
