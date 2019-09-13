package com.sec.gui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.sec.actionhandler.AppointmentHandler;
import com.sec.entity.Student;

import java.awt.Dimension;
import java.util.Queue;
import java.util.Vector;

/**
 * References:
 * This application's GUI functionality is referred from the following sites/links:
 * 1) Class Lecture's sample codes
 * 2) Video Tutorial for Swing (GUI) Programming by 'Cave of Programming' - https://www.youtube.com/watch?v=jUEOWVjnIR8&list=PL3D7046DF2257751F
 * 3) For updating/Initializing the JTables - http://www.java2s.com/Tutorial/Java/0240__Swing/TochangecellcontentsincodesetValueAtObjectvalueintrowintcolumnmethodofJTable.htm
 * 4) Box Layout Usage - https://examples.javacodegeeks.com/desktop-java/swing/java-swing-boxlayout-example/ 
 */

/**
 * This class contains the GUI elements for showing status of the queues.
 *
 */
public class QueueStatusPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private AppointmentHandler appointmentHandler;
	
	private JTable upcomingStudentTable;
	private JTable bannedStudentTable;
	private JTable attendedStudentTable;
	private JLabel upcomingStudentLabel = new JLabel("Next in Queue: ");
	private JLabel bannedStudentLabel = new JLabel("Banned: ");
	private JLabel attendedStudentLabel = new JLabel("Appointment completed: ");
	DefaultTableModel model1;
	DefaultTableModel model2;
	DefaultTableModel model3;
	
	public QueueStatusPanel(AppointmentHandler handler) {
				
		appointmentHandler = handler;
		
		initializeTables1();
		initializeTables2();
		initializeTables3();
		
		updateAllTables();
		BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxlayout);
		
		setBorder(BorderFactory.createTitledBorder("Queue Status"));	
		
		add(upcomingStudentLabel);
		add(Box.createRigidArea(new Dimension(0, 5)));
		JScrollPane sp1 = new JScrollPane(upcomingStudentTable); 
        add(sp1);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(attendedStudentLabel);
		add(Box.createRigidArea(new Dimension(0, 5)));
        JScrollPane sp2 = new JScrollPane(attendedStudentTable); 
        add(sp2);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(bannedStudentLabel);
		add(Box.createRigidArea(new Dimension(0, 5)));
        JScrollPane sp3 = new JScrollPane(bannedStudentTable); 
        add(sp3);	
		
	}
	
	/**
	 * Updates all the three JTables
	 */
	public void updateAllTables() {
		updateUpcomingStudentTable();
		updateAttendedStudentTable();
		updateBannedStudentTable();
	}
	
	/**
	 * Initialize current student status table
	 */
	public void initializeTables1() {
		Vector<String> rowOne = new Vector<String>();
	    rowOne.addElement("");
	    rowOne.addElement("");
	    rowOne.addElement("");
	    rowOne.addElement("");
	    rowOne.addElement("");
	    
	    Vector<String> rowTwo = new Vector<String>();
	    rowTwo.addElement("");
	    rowTwo.addElement("");
	    rowTwo.addElement("");
	    rowTwo.addElement("");
	    rowTwo.addElement("");
	    
	    Vector<String> rowThree = new Vector<String>();
	    rowThree.addElement("");
	    rowThree.addElement("");
	    rowThree.addElement("");
	    rowThree.addElement("");
	    rowThree.addElement("");
	    
	    Vector<String> rowFour = new Vector<String>();
	    rowFour.addElement("");
	    rowFour.addElement("");
	    rowFour.addElement("");
	    rowFour.addElement("");
	    rowFour.addElement("");
	    
	    Vector<Vector<String>> rowData1 = new Vector<Vector<String>>();
	    rowData1.addElement(rowOne);
	    rowData1.addElement(rowTwo);
	    rowData1.addElement(rowThree);
	    rowData1.addElement(rowFour);
	    
	    Vector<String> columnNames = new Vector<String>();
	    columnNames.addElement("S.No.");
	    columnNames.addElement("Name");
	    columnNames.addElement("Email");
	    columnNames.addElement("Question");
	    columnNames.addElement("Status");
	   
	    model1 = new DefaultTableModel(rowData1, columnNames);
	    
	    upcomingStudentTable = new JTable(model1);
	    
	}
	
	/**
	 * Initialize attended student status table
	 */	
	public void initializeTables2() {
		Vector<String> rowOne = new Vector<String>();
	    rowOne.addElement("");
	    rowOne.addElement("");
	    rowOne.addElement("");
	    rowOne.addElement("");
	    rowOne.addElement("");
	    
	    Vector<String> rowTwo = new Vector<String>();
	    rowTwo.addElement("");
	    rowTwo.addElement("");
	    rowTwo.addElement("");
	    rowTwo.addElement("");
	    rowTwo.addElement("");
	    
	    Vector<String> rowThree = new Vector<String>();
	    rowThree.addElement("");
	    rowThree.addElement("");
	    rowThree.addElement("");
	    rowThree.addElement("");
	    rowThree.addElement("");
	    
	    Vector<String> rowFour = new Vector<String>();
	    rowFour.addElement("");
	    rowFour.addElement("");
	    rowFour.addElement("");
	    rowFour.addElement("");
	    rowFour.addElement("");
	    
	    Vector<Vector<String>> rowData1 = new Vector<Vector<String>>();
	    rowData1.addElement(rowOne);
	    rowData1.addElement(rowTwo);
	    rowData1.addElement(rowThree);
	    rowData1.addElement(rowFour);
	    
	    Vector<String> columnNames = new Vector<String>();
	    columnNames.addElement("S.No.");
	    columnNames.addElement("Name");
	    columnNames.addElement("Email");
	    columnNames.addElement("Question");
	    columnNames.addElement("Status");
	    	    
	    model2 = new DefaultTableModel(rowData1, columnNames);
	    attendedStudentTable = new JTable(model2);
	    
	}
	
	/**
	 * Initialize ban student status table
	 */
	public void initializeTables3() {
		Vector<String> rowOne = new Vector<String>();
	    rowOne.addElement("");
	    rowOne.addElement("");
	    rowOne.addElement("");
	    rowOne.addElement("");
	    rowOne.addElement("");
	    
	    Vector<String> rowTwo = new Vector<String>();
	    rowTwo.addElement("");
	    rowTwo.addElement("");
	    rowTwo.addElement("");
	    rowTwo.addElement("");
	    rowTwo.addElement("");
	    
	    Vector<String> rowThree = new Vector<String>();
	    rowThree.addElement("");
	    rowThree.addElement("");
	    rowThree.addElement("");
	    rowThree.addElement("");
	    rowThree.addElement("");
	    
	    Vector<String> rowFour = new Vector<String>();
	    rowFour.addElement("");
	    rowFour.addElement("");
	    rowFour.addElement("");
	    rowFour.addElement("");
	    rowFour.addElement("");
	    
	    Vector<Vector<String>> rowData1 = new Vector<Vector<String>>();
	    rowData1.addElement(rowOne);
	    rowData1.addElement(rowTwo);
	    rowData1.addElement(rowThree);
	    rowData1.addElement(rowFour);
	    
	    Vector<String> bannedColumnNames = new Vector<String>();
	    bannedColumnNames.addElement("S.No.");
	    bannedColumnNames.addElement("Name");
	    bannedColumnNames.addElement("Email");
	    bannedColumnNames.addElement("Stauts");
	    bannedColumnNames.addElement("Banned Until");
	    
	    model3 = new DefaultTableModel(rowData1, bannedColumnNames);
	    
	    bannedStudentTable = new JTable(model3);
	}
	
	
	/**
	 * update the current student status table
	 */
	public void updateUpcomingStudentTable() {			
		Queue<Student> upcomingStudents = appointmentHandler.getCurrentStudentQueue();
		
		int i = 0;
		for (Student student: upcomingStudents) {
			
			model1.setValueAt(Integer.toString(i+1),i,0);
			model1.setValueAt(student.getStudentName(),i,1);
			model1.setValueAt(student.getEmail(),i,2);
			model1.setValueAt(student.getQuestion(),i,3);
			model1.setValueAt(student.getStatus(),i,4);	
			i++;
		}	
		
		while(i<4) {
			model1.setValueAt("",i,0);
			model1.setValueAt("",i,1);
			model1.setValueAt("",i,2);
			model1.setValueAt("",i,3);
			model1.setValueAt("",i,4);	
			i++;			
		}		
	}
	
	/**
	 * update the attended student status table
	 */
	public void updateAttendedStudentTable() {		
		
		Queue<Student> upcomingStudents = appointmentHandler.getAttendedStudentQueue();
				
		int i = 0;
		for (Student student: upcomingStudents) {
			
			model2.setValueAt(Integer.toString(i+1),i,0);
			model2.setValueAt(student.getStudentName(),i,1);
			model2.setValueAt(student.getEmail(),i,2);
			model2.setValueAt(student.getQuestion(),i,3);
			model2.setValueAt(student.getStatus(),i,4);	
			i++;
		}	
		
		while(i<4) {
			model2.setValueAt("",i,0);
			model2.setValueAt("",i,1);
			model2.setValueAt("",i,2);
			model2.setValueAt("",i,3);
			model2.setValueAt("",i,4);	
			i++;			
		}	
		
	}
	
	/**
	 * update the banned student status table
	 */
	public void updateBannedStudentTable() {			
		Queue<Student> upcomingStudents = appointmentHandler.getBanStudentQueue();
				
		int i = 0;
		for (Student student: upcomingStudents) {
			
			model3.setValueAt(Integer.toString(i+1),i,0);
			model3.setValueAt(student.getStudentName(),i,1);
			model3.setValueAt(student.getEmail(),i,2);
			model3.setValueAt(student.getStatus(),i,3);
			model3.setValueAt(student.getBannedUntilDate(),i,4);	
			i++;
		}	
		
		while(i<4) {
			model3.setValueAt("",i,0);
			model3.setValueAt("",i,1);
			model3.setValueAt("",i,2);
			model3.setValueAt("",i,3);
			model3.setValueAt("",i,4);	
			i++;			
		}	
		
	}
	
	
}
