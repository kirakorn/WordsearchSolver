package com.yuval;

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

