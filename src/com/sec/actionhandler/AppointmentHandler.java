package com.sec.actionhandler;
import java.util.*;

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
 * This is responsible for maintaining the queue of appointments for the office hour.
 *
 */
public class AppointmentHandler {

	private Queue<Student> currentStudentQueue;
	private Queue<Student> banStudentQueue;
	private Queue<Student> attendedStudentQueue;

	public AppointmentHandler() {
		currentStudentQueue = new LinkedList<>();
		banStudentQueue = new LinkedList<>();
		attendedStudentQueue = new LinkedList<>();
	}

	/**
	 * 
	 * @return queue for all the current students
	 */
	public Queue<Student> getCurrentStudentQueue() {
		return currentStudentQueue;
	}

	/**
	 * 
	 * @return queue for all the banned students
	 */
	public Queue<Student> getBanStudentQueue() {
		return banStudentQueue;
	}

	/**
	 * 
	 * @return queue for all the attended students
	 */
	public Queue<Student> getAttendedStudentQueue() {
		return attendedStudentQueue;
	}

	/**
	 * 
	 * @param studentQueue set the queue for the current students
	 */
	public void setCurrentStudentQueue(Queue<Student> studentQueue) {
		this.currentStudentQueue = studentQueue;
	}

	/**
	 * Generate the random appointments and add these in the current queue
	 * @param numberOfReservation number of reservations to be generated
	 * @param enableRandomization flag to control the randomization for the appointment timings
	 * @param addLateStudent flag to control the appointment timing of the students.
	 * @return the number of reservation added in the current queue
	 */
	public Integer generateRandomAppointments(int numberOfReservation, boolean enableRandomization,
			boolean addLateStudent) {
		try {
			Random rand = new Random();
			boolean addQuestion = true;

			for (int i = 0; i < numberOfReservation; i++) {
				Student student = new Student();
				student.setStudentID(i + 1);
				student.setStudentName("Student_" + (i + 1));
				if (addQuestion) {
					student.setQuestion("How to solve problem number " + (i + 1) + " in the assignment?");
					addQuestion = false;
				} else {
					addQuestion = true;
				}
				student.setEmail("Student_" + (i + 1) + "@buffalo.edu");

				if (enableRandomization) {
					int random = rand.nextInt(3);
					if (random == 0) {
						student.setAppointmentTime(new Date(System.currentTimeMillis() - 11 * 60 * 1000));
					} else if (random == 1) {
						student.setAppointmentTime(new Date(System.currentTimeMillis() - 5 * 60 * 1000));
					} else if (random == 2) {
						student.setAppointmentTime(new Date(System.currentTimeMillis()));
					}
					
				} else {
					if (addLateStudent) {
						student.setAppointmentTime(new Date(System.currentTimeMillis() - 11 * 60 * 1000));
					} else {
						student.setAppointmentTime(new Date(System.currentTimeMillis() - 5 * 60 * 1000));
					}
				}

				currentStudentQueue.add(student);
			}
			return numberOfReservation;
		} catch (Exception ex) {
			return -1;
		}
	}

	/**
	 * handles the process when Student is absent 
	 * @return true if the process is completed successfully
	 */
	public boolean handleAbsent() {
		boolean isSuccessful = false;
		Student currentStudent = getCurrentAppointment();
		if (currentStudent != null) {
			Date currentTime = new Date(System.currentTimeMillis() - 10 * 60 * 1000);
			if (currentStudent.getAppointmentTime().compareTo(currentTime) < 0) {
				startProcessToMarkStudentAsBan();
			} else {
				moveStudentToTheBack();
			}
			isSuccessful = !isSuccessful;
		}
		return isSuccessful;
	}

	/**
	 * Moves the student to the back of current student queue
	 * @return true if the process is completed successfully
	 */
	public boolean moveStudentToTheBack() {
		if (currentStudentQueue.size() > 0) {
			Student student = currentStudentQueue.poll();
			student.setStatus(AppConstants.STATUS_ABSENT);
			currentStudentQueue.add(student);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * returns the Student which is at top of the current student queue
	 * @return the Student which is at top of the current student queue
	 */
	public Student getCurrentAppointment() {
		if (currentStudentQueue.size() > 0) {
			return currentStudentQueue.peek();
		} else {
			return null;
		}
	}

	/**
	 * remove and return the Student which is at top of the current student queue
	 * @return the Student which is at top of the current student queue
	 */
	public Student getAndRemoveAppointment() {
		if (currentStudentQueue.size() > 0) {
			return currentStudentQueue.poll();
		} else {
			return null;
		}
	}

	/**
	 * handles the process when the student is present
	 * @return true if the process is completed successfully
	 */
	public boolean startProcessToMarkStudentAsPresent() {
		Student student = getAndRemoveAppointment();
		if (student != null) {
			student.setStatus(AppConstants.STATUS_PRESENT);
			attendedStudentQueue.add(student);
			return true;
		} else {
			return false;
		}

	}

	/**
	 * handles the process to ban the student
	 * @return true if the process is completed successfully
	 */
	public boolean startProcessToMarkStudentAsBan() {
		Student student = getAndRemoveAppointment();
		if (student != null) {
			student.banStudent(true);
			student.setStatus(AppConstants.STATUS_BANNED);
			banStudentQueue.add(student);
			return true;
		} else {
			return false;
		}

	}

}
