package jsong00505.study.leetcode.no1;

import jsong00505.core.utils.CheckStringFormat;

public class StringToInteger {
	/*
	 * 
	 * Implement atoi to convert a string to an integer.
	 * 
	 * Hint: Carefully consider all possible input cases. 
	 * If you want a challenge, 
	 * please do not see below and ask yourself what are the possible input cases.
	 * 
	 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
	 * You are responsible to gather all the input requirements up front.
	 * 
	 * Update (2015-02-10):
	 * The signature of the C++ function had been updated. 
	 * If you still see your function signature accepts a const char * argument,
	 * please click the reload button to reset your code definition.
	 * 
	 * problem1: WHITESPACE
	 * Reference: removing whitespace from strings in java
	 * http://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
	 * 
	 * problem2: exist non numeric strings in the string with numbers
	 * 
	 * problem3: plus/minus symbol
	 * 
	 * problem4: strip whitespaces
	 * 
	 * problem5: overflow
	 * 
	 * problems: you could see how many errors I faces. *Shake My Head*
	 * I need to fix my whole logic. (03/11/16)
	 * 
	 * 
	 * 
	 * Beats 1.52% of java submissions. I need to refactor this code
	 */
    public static int myAtoi(String str) {
    	int result = 0;
    	double doubleVal = 0;
    	CheckStringFormat csf = new CheckStringFormat();
    	try {
    		//str = str.replaceAll("\\s+",""); 
    		str = str.trim();
    		//result = Integer.parseInt(str);
    		if(str.contains("e") || str.contains("E")) {
    			// bug fix: case "   -115579378e25"
    			throw new NumberFormatException();
    		} else {
        		doubleVal = Double.parseDouble(str);
    		}
    	}catch(NumberFormatException nfe) {
    	    if("".equals(str)){
    	        doubleVal = 0;
    	    }else if(csf.isNonNumericString(str)) {
    	    
    	        doubleVal = 0;
    	    }else if(str.startsWith("+") || str.startsWith("-")) {
    	        if(csf.isNumericString(str.substring(1))) {
    	            if(str.startsWith("+")) {
    	                doubleVal = 2147483647;
    	            }
    	            else if(str.startsWith("-")) {
    	                doubleVal = - 2147483648;
    	            }
    	        } else {
    	            for(int i = 1;i<str.length();i++) {
    	            	// bug fix: case "+-2"
    	            	if(i == 1 && csf.isNonNumericString(""+str.charAt(i))) {
    	            		doubleVal = 0;
    	            		break;
    	            	} else if(csf.isNonNumericString(""+str.charAt(i))) {
    	                    doubleVal = Double.parseDouble(str.substring(1,i));
    	                    if(str.startsWith("-")) {
    	                        doubleVal = doubleVal * (-1);
    	                    }
    	                    break;
    	                }
    	            }
    	        }
    	    }else if(csf.isNumericString(str)){
    	        doubleVal = 2147483647;
    	    }else if(csf.isNonNumericString(str.substring(0,1))){
    	        doubleVal = 0;
    	    }else {
    	        for(int i = 0;i<str.length();i++) {
                    if(csf.isNonNumericString(""+str.charAt(i))) {
                    	// bug fix: case "       11504069574n"
                        doubleVal = Double.parseDouble(str.substring(0,i));
                        break;
                    }
                }
    	    }
    	}finally {
    	    if(doubleVal >= 2147483647){
    	        result = 2147483647;
    	    }else if(doubleVal <= -2147483648){
    	        result = -2147483648;
    	    }else {
    	        result = (int)doubleVal;
    	    }
    	}
        return result;
    }
}
