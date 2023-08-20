import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FindSuspect extends JFrame {
	private JFrame frame = new JFrame(); //main frame of window
	private JPanel panel; //main panel of main frame
	private JTextField nameField; //text field that gets the name of the Suspect
	private JButton findButton; //button for finding the Suspect
	private JButton visualizeButton; //button for visualize network
	
	private Registry registry;
	
	public FindSuspect(Registry aRegistry) {
		registry = aRegistry;
		
		panel = new JPanel();
		nameField = new JTextField("Please enter suspect's name", 16);
		findButton = new JButton("Find");
		
		visualizeButton = new JButton("Visualize Network");
		
		panel.add(nameField);
		panel.add(findButton);
		panel.add(visualizeButton);
		
		ButtonListener listener = new ButtonListener();	//setting up the listener for our button
		findButton.addActionListener(listener);
		visualizeButton.addActionListener(listener);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setTitle("Find Suspect");
		frame.setSize(300,150);
		frame.setLocation(300, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String name = nameField.getText();
			
			if(e.getSource()==findButton) {
				if(registry.thereIsSuspect(name).getName() == "0") //checking if the suspect are in our registry
					JOptionPane.showMessageDialog(null, "Suspect " + name + " Not Found"); //printing error message
				else //printing suspect's info
				{
					Suspect aSuspect = registry.thereIsSuspect(name);
					new SuspectPage(aSuspect,registry,frame);	
					frame.setVisible(false);
				}
			}
			//visualize button
			else {
				new SuspectsNetwork(registry,frame);
			}

		}
		
	}
}
