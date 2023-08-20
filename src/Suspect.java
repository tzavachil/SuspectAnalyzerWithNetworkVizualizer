import java.util.ArrayList;

public class Suspect {
	private String name;
	private String codename;
	private String country;
	private String city;
	private ArrayList<String> phones = new ArrayList<>();
	private ArrayList<Suspect> partners = new ArrayList<>();
	
	public Suspect(String name, String codename, String country, String city)
	{
		this.name = name;
		this.codename = codename;
		this.country = country;
		this.city = city;
	}
	
	//Constructor for empty Suspect object 
	public Suspect()
	{
		
	}
	
	public ArrayList<String> getPhones(){
		return this.phones;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCodeName() {
		return this.codename;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public ArrayList<Suspect> getPartners(){
		return this.partners;
	}
	
	//Adding a phone number to the suspect's phones list
	public void addNumber(String phone)
	{
		this.phones.add(phone);
	}
	
	//Adding a possible suspect to suspect's partners list
	public void addPossibleSuspect(Suspect aSuspect)
	{
		if(!this.isConnectedTo(aSuspect))
			this.partners.add(aSuspect);
	}
	
	//Returns true if 2 suspects are partners and false if they aren't
	public boolean isConnectedTo(Suspect aSuspect)
	{
		if(this.partners.contains(aSuspect))
			return true;
		return false;
	}
	
	//Returns a array list o suspects that they are partners with the suspect "aSuspect"
	public ArrayList<Suspect> getCommonPartners(Suspect aSuspect)
	{
		ArrayList<Suspect> commonPartners = new ArrayList<>();
		
		for(Suspect aPartner: this.partners)
		{
			if(aSuspect.isConnectedTo(aPartner))
				commonPartners.add(aPartner);
		}
		
		return commonPartners;
	}
	
	//Prints info about suspect's partners
	public void printPartners()
	{
		System.out.println("Partners of " + this.getName() );
		for(Suspect aPartner: partners)
		{
			System.out.print("Name: " + aPartner.getName() + " CodeName: " + aPartner.getCodeName());
			if(this.country.equals(aPartner.country))
					System.out.print("*");
			System.out.println("");
		}
	}
	
	//Returns true if this number belongs to this suspect
	public boolean has(String number)
	{
		if(this.phones.contains(number))
			return true;
		return false;
	}
	
	//Returns the number of suspect's partners
	public int getPartnersNum()
	{
		return this.partners.size();
	}	
	
	//Returns a List partners' partners who are not connected with the Suspect 
	public ArrayList<Suspect> rtrnPartner()
	{
		ArrayList<Suspect> aList = new ArrayList<>();
		
		for(Suspect aSuspect: this.partners) { //going through Suspect's partners
			for(Suspect aPartner : aSuspect.partners) { //going through Partner's Partners
				if(!this.partners.contains(aPartner) && !aList.contains(aPartner) && aPartner!=this) 
					//we want the partner (aPartner) neither to be connected with the Suspect neither add them more than one time. Of course we don't want him to be the same Suspect
					aList.add(aPartner);
			}
		}
		
		return aList;
	}
	
	//Prints Info about the Suspect (name,codename)
	public String printInfo() {
		return this.name + "," + this.codename + "\n";
	}
}
