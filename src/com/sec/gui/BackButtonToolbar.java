package com.sec.gui;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * References:
 * This application's GUI functionality is referred from the following sites/links:
 * 1) Class Lecture's sample codes
 * 2) Video Tutorial for Swing (GUI) Programming by 'Cave of Programming' - https://www.youtube.com/watch?v=jUEOWVjnIR8&list=PL3D7046DF2257751F
 * 3) For updating/Initializing the JTables - http://www.java2s.com/Tutorial/Java/0240__Swing/TochangecellcontentsincodesetValueAtObjectvalueintrowintcolumnmethodofJTable.htm
 * 4) Box Layout Usage - https://examples.javacodegeeks.com/desktop-java/swing/java-swing-boxlayout-example/ 
 */


/**
 * This class contains the GUI elements for the back button toolbar used in the Queue status page.
 *
 */
public class BackButtonToolbar extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JButton nextButton;
	private JButton closeButton;
		
	private ButtonListener listener; 
	
	public BackButtonToolbar() {
		nextButton = new JButton("Next");
		closeButton = new JButton("Close");
		nextButton.addActionListener(this);
		closeButton.addActionListener(this);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		//add(nextButton);
		add(closeButton);
	}
	
	public void setButtonListener(ButtonListener buttonListner) {
		this.listener = buttonListner;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == nextButton) {
			if(listener != null) {
				listener.actionEmitted("next");
			}
		}
		else if(clicked == closeButton) {
			if(listener != null) {
				listener.actionEmitted("close");
			}
		}
	}	
	
}
