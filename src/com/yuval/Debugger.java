package com.yuval;

// All software protected under the Apache License version 2.0
public class Debugger {
    public static boolean isEnabled(){
        return false;
    }
    
	public  void log(Object o){
	    if(Debugger.isEnabled()) {
	        System.out.println(o.toString());
	    }           
	}
}

