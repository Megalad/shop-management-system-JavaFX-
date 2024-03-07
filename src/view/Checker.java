package view;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Checker {
	
	
	public static boolean isValidPh(String ph)
	{
//		String reg="^(09)([0-9]){9}$";
//		
//		String reg1="^(09|\\\\+?950?9|\\\\+?95950?9)\\\\d{7,9}$";
		String ooredoo = "^(09|\\+?959)9[67]\\d{7}$";
		String telenor = "^(09|\\+?959)7[987]\\d{7}$";
		String mpt = "^(09|\\+?959)(5\\d{6}|4\\d{7,8}|2\\d{6,8}|3\\d{7,8}|6\\d{6}|8\\d{6}|7\\d{7}|9[019]\\d{5,6})$";

		
		if(Pattern.matches(ooredoo, ph) || Pattern.matches(telenor, ph) || Pattern.matches(mpt, ph))
		{
			return true;
		}
		
		return false;
		
	}
	public static boolean showConfirmation(String message) {
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText(message);

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
	
//	public static boolean isValidPh(String ph)
//	{
//		String reg="^(09)([0-9]){9}$";
//		
//		return Pattern.matches(reg, ph);
//		
//	}
	
	public static boolean isValidName(String n)
	{  
		String reg = "^([A-Z][a-z]+\\s?)+([A-Z][a-z]+\\s?)*$";
		return Pattern.matches(reg,n);
	}
	public static boolean isValidPw(String password)
	{
		String reg="^.{8,}$";
		
		return Pattern.matches(reg, password);
	}
	public static boolean isValidPrice(String price)
	{
		String reg="^[0-9]{3,}$";
		
		return Pattern.matches(reg, price);
	}
	public static boolean isValidDOB(LocalDate dob)
	{
		
		  LocalDate currentDate = LocalDate.now();

	        // Calculate the period between the date of birth and current date
	      Period period = Period.between(dob, currentDate);

	        // Check if the person is 18 years or older
	      return period.getYears() >= 18;
	}
	public static void alertMessage(String title,String message)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.print(isValidPh("09444200661"));

	}

}
