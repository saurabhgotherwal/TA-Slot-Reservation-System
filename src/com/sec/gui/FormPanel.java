package com.sec.gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;

import javax.swing.*;

import com.sec.actionhandler.AppointmentHandler;
import com.sec.entity.Student;
import com.sec.util.AppConstants;

/**
 * References:
 * This application's GUI functionality is referred from the following sites/links:
 * 1) Class Lecture's sample codes
 * 2) Video Tutorial for Swing (GUI) Programming by 'Cave of Programming' - https://www.youtube.com/watch?v=jUEOWVjnIR8&list=PL3D7046DF2257751F
 * 3) For updating/Initializing the JTables - http://www.java2s.com/Tutorial/Java/0240__Swing/TochangecellcontentsincodesetValueAtObjectvalueintrowintcolumnmethodofJTable.htm
 * 4) Box Layout Usage - https://examples.javacodegeeks.com/desktop-java/swing/java-swing-boxlayout-example/ 
 */

/**
 * This class contains the GUI elements for showing the students details.
 *
 */
public class FormPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private AppointmentHandler appointmentHandler;
	private Student currentStudent;
	
	private JLabel studentNameLabel;
	private JLabel studentEmailLabel;
	private JLabel studentQuestionLabel;
	private JLabel studentAppointmentTimeLabel;
	private JLabel blankLabel1;
	private JLabel blankLabel2;
	
	private JLabel studentNameValueLabel;
	private JLabel studentEmailValueLabel;
	private JLabel studentQuestionValueLabel;
	private JLabel studentAppointmentTimeValueLabel;
	
	
	public FormPanel(AppointmentHandler handler) {
		Dimension dimension = getPreferredSize();
		dimension.width = 250;
		setPreferredSize(dimension);
		
		appointmentHandler = handler;
		
		studentNameLabel = new JLabel("Name: ");
		studentEmailLabel = new JLabel("Email: ");
		studentQuestionLabel = new JLabel("Question: ");
		studentAppointmentTimeLabel = new JLabel("Appointment Time: ");
		blankLabel1 = new JLabel("");
		blankLabel2 = new JLabel("");
		
		studentNameValueLabel = new JLabel("");
		studentEmailValueLabel = new JLabel("");
		studentQuestionValueLabel = new JLabel("");
		studentAppointmentTimeValueLabel = new JLabel("");
		
		displayCurrentAppointment();		
		
		setBorder(BorderFactory.createTitledBorder("Student Details"));
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();	
		
		gc.weightx = 1;
		gc.weighty = 0.5;
		
		gc.gridx = 0;
		gc.gridy = 0;		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(blankLabel1, gc);
		
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(studentNameLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(studentNameValueLabel, gc);
		
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(studentEmailLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(studentEmailValueLabel, gc);
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(studentQuestionLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(studentQuestionValueLabel, gc);
		
		gc.weightx = 1;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0,0,0,5);
		add(studentAppointmentTimeLabel, gc);
		
		gc.gridx = 1;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(studentAppointmentTimeValueLabel, gc);
		
		gc.weightx = 1;
		gc.weighty = 0.5;
		gc.gridx = 1;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(blankLabel2, gc);
	}
	
	/**
	 * Displays the students who is on the top of the current student queue
	 * @return the student which is being displayed
	 */
	public Student displayCurrentAppointment() {		
				
		currentStudent = appointmentHandler.getCurrentAppointment();
		
		SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.DATE_TIME_FORMAT);		
		
		if(currentStudent != null) {			
			studentNameValueLabel.setText(currentStudent.getStudentName());			
			studentEmailValueLabel.setText(currentStudent.getEmail());
			studentQuestionValueLabel.setText(currentStudent.getQuestion());
			studentAppointmentTimeValueLabel.setText(formatter.format(currentStudent.getAppointmentTime()));
			
		}
		else {
			JOptionPane.showMessageDialog(this,"There is no student in the queue.");
			studentNameValueLabel.setText("");
			studentEmailValueLabel.setText("");
			studentQuestionValueLabel.setText("");
			studentAppointmentTimeValueLabel.setText("");
			System.exit(0);
			
		}	
		
		return currentStudent;
	}
}
