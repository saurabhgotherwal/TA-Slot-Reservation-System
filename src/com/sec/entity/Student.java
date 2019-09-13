package com.sec.entity;

import java.text.SimpleDateFormat;
import java.util.*;

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
 * The Student class holds necessary information about a Student 
 */
public class Student {

	private int studentID;
	private String studentName;
	private String email;
	private String question;
	private Date appointmentTime = null;
	private boolean isBanned;
	private Date bannedUntilDate;
	private String status = AppConstants.STATUS_UPCOMING;
	
	
	/**
	 * 
	 * @return date until a student is banned.
	 */
	public String getBannedUntilDate() {
		SimpleDateFormat formatter = new SimpleDateFormat(AppConstants.DATE_FORMAT);
		String result = "";
		if (bannedUntilDate != null) {
			result = formatter.format(bannedUntilDate);
		} else {
			result = AppConstants.STATUS_NOT_BANNED;
		}

		return result;
	}

	/**
	 * 
	 * @param bannedUntilDate set the date until a student is banned
	 */
	public void setBannedUntilDate(Date bannedUntilDate) {
		this.bannedUntilDate = bannedUntilDate;
	}

	/**
	 * 
	 * @return true if the student is banned else false
	 */
	public boolean isBanned() {
		return isBanned;
	}

	/**
	 * 
	 * @param isBanned set to true if student is banned else false
	 */
	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	/**
	 * 
	 * @return appointment time of the student
	 */
	public Date getAppointmentTime() {
		return appointmentTime;
	}

	/**
	 * 
	 * @param appointmentTime sets the appointment time of the student
	 */
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	/**
	 * 
	 * @return email id of the student
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email set the email id of the student
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return name of the student
	 */
	public String getStudentName() {
		return studentName;
	}
	
	
	/**
	 * 
	 * @param studentName set the name of the student
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * 
	 * @return the id of the student 
	 */
	public int getStudentID() {
		return studentID;
	}
	

	/**
	 * 
	 * @param studentID set the id of the student
	 */
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	/**
	 * 
	 * @return the question of the student
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * 
	 * @param question set the question of the student
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * 
	 * @return the current status of the student's appointment
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status sets the current status of the student's appointment
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	/**
	 * handle the process to ban the student
	 * @param ban set to true if student is banned
	 * @return true if the student is successfully banned
	 */
	public boolean banStudent(boolean ban) {
		try {
			if (ban) {
				this.isBanned = true;
				this.bannedUntilDate = new Date(System.currentTimeMillis());
			} else {
				this.isBanned = false;
				this.bannedUntilDate = null;
			}
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
