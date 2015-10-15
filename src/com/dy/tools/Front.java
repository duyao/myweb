package com.dy.tools;

import java.io.UnsupportedEncodingException;

public class Front {
	public static String getNewString(String str){
		String newString="";
		try {
			newString= new String(str.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newString;
	}

}
