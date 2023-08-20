import java.util.ArrayList;

public class Registry {
	private ArrayList<Communication> communications = new ArrayList<>();
	private ArrayList<Suspect> suspects = new ArrayList<>();
	
	//Adding a Suspect in the array list of suspects
	public void addSuspect(Suspect aSuspect)
	{
		this.suspects.add(aSuspect);
	}
	
	//Adding a Communication in the array list of Communications and connects the 2 partners of this Communication
	public void addCommunication(Communication aCommunication)
	{
		Suspect first_suspect = new Suspect();
		Suspect second_suspect = new Suspect();
		
		this.communications.add(aCommunication);
		for(Suspect aSuspect: suspects)
		{
			if(aSuspect.has(aCommunication.getNumber1()))
				first_suspect = aSuspect;
			if(aSuspect.has(aCommunication.getNumber2()))
				second_suspect = aSuspect;
		}
		first_suspect.addPossibleSuspect(second_suspect);
		second_suspect.addPossibleSuspect(first_suspect);
	}
	
	public Suspect getSuspectWithMostPartners()
	{
		Suspect aSuspect = new Suspect();
		int counter = 0;
		
		for(Suspect suspect: suspects)
		{
			if(suspect.getPartnersNum()>=counter)
			{
				counter = suspect.getPartnersNum();
				aSuspect = suspect;
			}
		}
		return aSuspect;
	}
	
	public PhoneCall getLongestPhoneCallBetween(String number1, String number2)
	{
		PhoneCall aCall = new PhoneCall();
		
		for(Communication call: communications)
		{
			if(call.isPhoneCall())
			{
				if(((call.getNumber1().equals(number1) && call.getNumber2().equals(number2)) 			//Checking if this call belongs to this 2 phone numbers
						|| (call.getNumber1().equals(number2) && call.getNumber2().equals(number1))) 	//   >>                  >>                   >> 
						&& call.getDuration() > aCall.getDuration())									//Checking which call has the longest duarion
				{
					aCall = (PhoneCall)call; //converting a Communication to a PhoneCall
				}	
			}
		}
		
		return aCall;
	}
	
	public ArrayList<SMS> getMessagesBetween(String number1, String number2)
	{
		ArrayList<SMS> sms = new ArrayList<>();
		
		for(Communication aSms: communications)
		{
			if(!aSms.isPhoneCall())
			{
				if(((aSms.getNumber1().equals(number1) && aSms.getNumber2().equals(number2)) 			//Checking if this call belongs to this 2 phone numbers
						|| (aSms.getNumber1().equals(number2) && aSms.getNumber2().equals(number1)))	//   >>                  >>                   >>  
						&& aSms.containsStrings())														//Checking if this SMS contains a specific String (Stings are defined inside the method) 
				{
					sms.add((SMS)aSms); //converting a Communication to a SMS
				}
			}
		}
		
		return sms;
	}
	
	//Returns a array list of suspects who are from the same country
	public ArrayList<Suspect> getSuspectsFromCountry(String country)
	{
		ArrayList<Suspect> Suspects = new ArrayList<>();
		
		for(Suspect aSuspect: suspects)
		{
			if(aSuspect.getCountry().equals(country))
				Suspects.add(aSuspect);
		}
		
		return Suspects;
	}
	
	//Prints info of suspects who are from the same country
	public void printSuspectsFromCountry(String country)
	{
		ArrayList<Suspect> Suspects = this.getSuspectsFromCountry(country);
		
		System.out.println("Suspects coming from " + country);
		for(Suspect aSuspect: Suspects)
		{
			System.out.println(aSuspect.getName() + " (" + aSuspect.getCodeName() + ")");
		}
		
	}
	
	//Checking if a Suspect is in a registry. Returns the Suspect if it finds him. Else it returns an empty Suspect(form---> 0,0,0,0) 
	public Suspect thereIsSuspect(String name) {
		Suspect suspect = new Suspect("0","0","0","0"); //creating an empty suspect
		for(Suspect aSuspect: suspects) {
			if(name.equals(aSuspect.getName())) {
				suspect = aSuspect;
			}
		}
		return suspect;
	}
	
	public ArrayList<Suspect> getSuspects(){
		ArrayList<Suspect> suspects = new ArrayList<>();
		
		for(Suspect aSuspect: this.suspects)
			suspects.add(aSuspect);
			
		return suspects;
	} 
}
