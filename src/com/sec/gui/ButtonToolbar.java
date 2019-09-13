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
 * This class contains the GUI elements for the Present and Absent button.
 *
 */
public class ButtonToolbar extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JButton presentButton;
	private JButton absentButton;
	
	private ButtonListener listener; 
	
	public ButtonToolbar() {
		presentButton = new JButton("Present");
		absentButton = new JButton("Absent");
		
		presentButton.addActionListener(this);
		absentButton.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		add(presentButton);
		add(absentButton);
	}
	
	public void setButtonListener(ButtonListener buttonListner) {
		this.listener = buttonListner;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == presentButton) {
			if(listener != null) {
				listener.actionEmitted("Present");
			}
		}
		else if(clicked == absentButton) {
			if(listener != null) {
				listener.actionEmitted("Absent");
			}
		}
		
	}
	
	
}
