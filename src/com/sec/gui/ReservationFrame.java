package com.sec.gui;
import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;

import com.sec.actionhandler.AppointmentHandler;

/**
 * References:
 * This application's GUI functionality is referred from the following sites/links:
 * 1) Class Lecture's sample codes
 * 2) Video Tutorial for Swing (GUI) Programming by 'Cave of Programming' - https://www.youtube.com/watch?v=jUEOWVjnIR8&list=PL3D7046DF2257751F
 * 3) For updating/Initializing the JTables - http://www.java2s.com/Tutorial/Java/0240__Swing/TochangecellcontentsincodesetValueAtObjectvalueintrowintcolumnmethodofJTable.htm
 * 4) Box Layout Usage - https://examples.javacodegeeks.com/desktop-java/swing/java-swing-boxlayout-example/ 
 */

/**
 * This class contains the GUI elements for showing the JPanel used in the application.
 *
 */
public class ReservationFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtonToolbar toolbar;
	private BackButtonToolbar backToolbar;
	private FormPanel formPanel;
	private QueueStatusPanel queueStatusPanel;
	private AppointmentHandler appointmentHandler;
	
	public ReservationFrame() {
		super("Office hours reservation system");
		appointmentHandler = new AppointmentHandler();
		Random rand = new Random();
		int numberOfReservation = rand.nextInt(5);
		appointmentHandler.generateRandomAppointments(numberOfReservation, true, true);
		toolbar = new ButtonToolbar();
		backToolbar = new BackButtonToolbar();
		formPanel = new FormPanel(appointmentHandler);
		queueStatusPanel = new QueueStatusPanel(appointmentHandler);
		
		setLayout(new BorderLayout());
		
		
		backToolbar.setButtonListener(new ButtonListener() {
			@Override
			public void actionEmitted(String action) {
				if(action == "next") {
					formPanel.displayCurrentAppointment();
					getContentPane().remove(queueStatusPanel);
					getContentPane().remove(backToolbar);
					queueStatusPanel.setVisible(false);
					backToolbar.setVisible(false);
					getContentPane().add(formPanel, BorderLayout.CENTER);	
					getContentPane().add(toolbar, BorderLayout.SOUTH);
					formPanel.setVisible(true);
					toolbar.setVisible(true);
					invalidate();
					validate();
				}
				else if(action == "close") {
					System.exit(0);
				}
			}
		});
		
		toolbar.setButtonListener(new ButtonListener() {

			@Override
			public void actionEmitted(String action) {
				
				System.out.println(action);	
				
				if(action == "Present") {					
					appointmentHandler.startProcessToMarkStudentAsPresent();									
					
				}
				else if(action == "Absent"){
					
					appointmentHandler.handleAbsent();	
										
				}	
						
				queueStatusPanel.updateAllTables();
				getContentPane().remove(formPanel);
				getContentPane().remove(toolbar);
				toolbar.setVisible(false);
				formPanel.setVisible(false);
				getContentPane().add(queueStatusPanel, BorderLayout.CENTER);	
				getContentPane().add(backToolbar, BorderLayout.SOUTH);
				queueStatusPanel.setVisible(true);
				backToolbar.setVisible(true);
				invalidate();
				validate();
			}
		});
		
		add(formPanel, BorderLayout.CENTER);				
		add(toolbar, BorderLayout.SOUTH);
				
		setSize(600,450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
}
