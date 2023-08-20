import java.text.SimpleDateFormat;

public class SMS extends Communication {
	private String text;
	
	public SMS(String number1, String number2, int days, int month, int year, String text) {
		super(number1, number2, days, month, year);
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public boolean isPhoneCall()
	{
		return false;
	}

	//Checking if the SMS (this.text) contains one of the Strings inside command 'if'
	public boolean containsStrings()
	{
		if(this.text.contains("Bomb") || this.text.contains("Attack") || this.text.contains("Explosives") || this.text.contains("Gun"))
			return true;
		return false;
	}
	
	//Prints specific info about a PhoneCall
	public void printInfo(SimpleDateFormat aFormat)
	{			
		System.out.println("This SMS the followng info");
		super.printInfo(aFormat);
		System.out.println("Text: " + this.text);
	}
}
