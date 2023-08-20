import java.text.SimpleDateFormat;

public class PhoneCall extends Communication {
	private int duration;
	
	public PhoneCall(String number1, String number2, int days, int month, int year, int duration) {
		super(number1, number2, days, month, year);
		this.duration = duration;
	}
	
	//Constructor for empty PhoneCall object 
	public PhoneCall()
	{
		super();
		this.duration = 0;
	}
	
	public boolean isPhoneCall()
	{
		return true;
	}
	
	public int getDuration() {
		return this.duration;
	}

	//Prints specific info about a PhoneCall
	public void printInfo(SimpleDateFormat aFormat)
	{			
		System.out.println("This phone call has the followng info");
		super.printInfo(aFormat);
		System.out.println("Duration: " + this.duration);
	}
}
