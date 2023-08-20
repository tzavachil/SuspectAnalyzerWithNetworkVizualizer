import java.text.SimpleDateFormat;
import java.util.Date;

public class Communication {
	private String number1;
	private String number2;
	private Date date = new Date();
	
	public Communication(String number1, String number2, int days, int month, int year)
	{
		this.number1 = number1;
		this.number2 = number2;
		date.setMonth(month-1); //we have month-1 because January = 0
		date.setYear(year-1900); //we have year-1900 because this method begins from 1900
		date.setDate(days);
	}
	
	//Constructor for empty Communication object 
	public Communication()
	{
		
	}

	public String getNumber1() {
		return this.number1;
	}
	
	public String getNumber2() {
		return this.number2;
	}
	
	//With this method we allow the same method to operate on the subclasses. It returns true for a PhoneCall and false for a SMS
	public boolean isPhoneCall()
	{
		return true;
	}
	
	/*With this method we allow the same method to operate on the subclasses. 
	 * It returns the duration of a PhoneCall if Communication is a PhoneCall.
	 * If Communication is a SMS it returns 0*/
	public int getDuration()
	{
		return 0;
	}
	
	//Printing Communication's info
	public void printInfo(SimpleDateFormat aFormat)
	{
		System.out.println("Between " + this.getNumber1() + " --- " + this.getNumber2());
		System.out.println("on " + aFormat.format(this.date));
	}
	
	/*With this method we allow the same method to operate on the subclasses. 
	 * It returns true if the SMS contains a specific String (if Communication is a SMS).
	 * If Communication is a PhoneCall it returns false*/
	public boolean containsStrings()
	{
		return false;
	}
}
