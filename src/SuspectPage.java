import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SuspectPage extends JFrame {
	private JPanel mainPanel; //main panel of window. It has all the other panels of the class
	private JPanel firstPanel;
	private JPanel secondPanel;
	private JPanel thirdPanel;
	private JPanel fourthPanel;
	private JPanel fifthPanel;
	private JPanel sixthPanel;
	private JTextField nameField;			//field with Suspect's name(not editable)
	private JTextField codeNameField;		//field with Suspect's codename(not editable)
	private JList<String> numbersList;		//list with Suspect's phone numbers
	private JTextField numberField;			//text field for read a phone number
	private JTextArea smsField;				//text area: printing info about Suspect's phone numbers and the phone number above(not editable)
	private JButton findSmsButton;			//button for finding info mentioned above
	private JLabel partnersLabel;			//label ---> Partners
	private JTextArea infoField;			//info about Suspect's partners(not editable)
	private JLabel suggestedPartnersLabel;	//label ---> Suggested Partners
	private JTextArea infoField2;			//info about Suspect's suggested partners(not editable)
	private JTextArea countrySuspectsField;	//info about Suspects with the Suspect's country(not editable)
	private JButton returnButton;			//button for returning back on the previous search window
	
	private Registry registry;
	private Suspect suspect;
	private JFrame mainFrame;
	
	public SuspectPage(Suspect aSuspect, Registry aRegistry, JFrame frame) {
		mainFrame = frame;
		registry = aRegistry;
		suspect = aSuspect;
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		//first panel's objects
		firstPanel = new JPanel();
		
		nameField = new JTextField(suspect.getName(), 10);
		nameField.setEditable(false);
		codeNameField = new JTextField(suspect.getCodeName(), 10);
		codeNameField.setEditable(false);
		
		DefaultListModel listModel = new DefaultListModel();
		for(String aPhone: aSuspect.getPhones()) {
			listModel.addElement(aPhone);	//adding the Suspect's phone numbers in the model of our list
		}
		numbersList = new JList(listModel);	//setting up our list with our list model
		firstPanel.add(nameField);
		firstPanel.add(codeNameField);
		firstPanel.add(numbersList);
		
		//second panel's objects
		secondPanel = new JPanel();
		
		numberField = new JTextField("Enter phone number");
		smsField = new JTextArea(10,20);
		smsField.setEditable(false);
		findSmsButton = new JButton("Find SMS");
		ButtonListener listener = new ButtonListener();
		findSmsButton.addActionListener(listener);
		secondPanel.add(numberField);
		secondPanel.add(smsField);
		secondPanel.add(findSmsButton);
		
		//third panel's objects
		thirdPanel = new JPanel();
		
		partnersLabel = new JLabel("Partners");
		infoField = new JTextArea("", 10,20);
		infoField.setSize(300,300);
		infoField.setEditable(false);
		for(Suspect aPartner : aSuspect.getPartners()) 
			infoField.setText(infoField.getText() + aPartner.printInfo());
		thirdPanel.add(partnersLabel);
		thirdPanel.add(infoField);
		
		//fourth panel's objects
		fourthPanel = new JPanel();
		
		suggestedPartnersLabel = new JLabel("Suggested Partners ----->");
		infoField2 = new JTextArea("",5,20);
		infoField2.setSize(300, 400);
		infoField2.setEditable(false);
		for(Suspect aPartner : aSuspect.rtrnPartner())
			infoField2.setText(infoField2.getText() + aPartner.printInfo());
		fourthPanel.add(suggestedPartnersLabel);
		fourthPanel.add(infoField2);
		
		//fifth panel's objects
		fifthPanel = new JPanel();
		
		countrySuspectsField = new JTextArea("Suspects coming from " + aSuspect.getCountry() + "\n",5,20);
		for(Suspect aPartner : registry.getSuspectsFromCountry(aSuspect.getCountry()))
				countrySuspectsField.setText(countrySuspectsField.getText() + aPartner.printInfo());
		fifthPanel.add(countrySuspectsField);
		
		//sixth panel's object
		sixthPanel = new JPanel();
		
		returnButton = new JButton("Return to Search Screen");
		returnButton.addActionListener(listener);
		sixthPanel.add(returnButton);
		
		
		mainPanel.add(firstPanel);
		mainPanel.add(secondPanel);
		mainPanel.add(thirdPanel);
		mainPanel.add(fourthPanel);
		mainPanel.add(fifthPanel);
		mainPanel.add(sixthPanel);
		
		this.setContentPane(mainPanel);
		this.setVisible(true);
		this.setTitle("Suspect Page");
		this.setSize(500, 700);
		this.setLocation(200, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(findSmsButton)) {	//Action for FIND SMS button
				String numberText = numberField.getText(); //getting the text of our text field
				for(String phoneNumber: suspect.getPhones()) { //going through all Suspect's phone numbers
					for(SMS aSms: registry.getMessagesBetween(phoneNumber, numberText)) { //going through the Sms of the 2 phone numbers
						smsField.setText(smsField.getText() + "\n" + aSms.getText());	//setting up field's text, it's adding the previous message of the same field to continue writing something else
						smsField.setSize(smsField.getText().length(),smsField.getText().length());	//setting up field's size so it can be readed
					}
				} 
			}
			else {	//Action for Return button
				mainFrame.setVisible(true);
				setVisible(false);
			}
			
		}
		
	}
}
