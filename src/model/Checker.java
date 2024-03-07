package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class Checker {
	public static boolean isValidName(String n)
	{  
		String reg="^[A-Z][a-z]*([A-Z][a-z]+)*$";
		return Pattern.matches(reg,n);
	}
	public static String digestMsg(String str)
	{
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte arr[]=md.digest();
			return new String(arr);
			
			
			
			
		} catch (NoSuchAlgorithmException e) {
			
			return "Unsuccess";
		}
	}
	public static void main (String args[])
	{
		System.out.println(digestMsg("12345"));
		System.out.print(digestMsg("12342"));
	}

}
