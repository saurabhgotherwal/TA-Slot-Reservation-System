package com.sec.junittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sec.actionhandler.AppointmentHandler;
import com.sec.entity.Student;
import com.sec.util.AppConstants;

/**
 * 
 *
 */
class AppointmentHandlerTest {

	static AppointmentHandler appointmentHandler;
	static final Integer DEFAULT_QUEUE_SIZE = 4;
	static final Boolean FALSE = Boolean.FALSE;
	static final Boolean TRUE = Boolean.TRUE;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		appointmentHandler = new AppointmentHandler();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void generateRandomAppointmentsTest() {
		appointmentHandler.getCurrentStudentQueue().clear();
		int result = appointmentHandler.generateRandomAppointments(DEFAULT_QUEUE_SIZE, TRUE, FALSE);
		assertEquals(appointmentHandler.getCurrentStudentQueue().size(), DEFAULT_QUEUE_SIZE);
		assertNotEquals(-1, result);
		appointmentHandler.getCurrentStudentQueue().clear();
		assertEquals(false, appointmentHandler.handleAbsent());
		appointmentHandler.generateRandomAppointments(2, FALSE, FALSE);
		Student student = appointmentHandler.getCurrentStudentQueue().peek();
		assertNotEquals(AppConstants.STATUS_ABSENT, student.getStatus());
		assertEquals(true, appointmentHandler.handleAbsent());
		assertEquals(2, appointmentHandler.getCurrentStudentQueue().size());
		assertNotEquals(student.getStudentID(), appointmentHandler.getCurrentStudentQueue().poll().getStudentID());
		assertEquals(student.getStudentID(), appointmentHandler.getCurrentStudentQueue().poll().getStudentID());
		assertEquals(AppConstants.STATUS_ABSENT, student.getStatus());	
	}

	@Test
	public void handleNoStudentTest() {
		appointmentHandler.getCurrentStudentQueue().clear();
		assertEquals(FALSE, appointmentHandler.handleAbsent());
		assertEquals(FALSE, appointmentHandler.startProcessToMarkStudentAsPresent());
	}

	@Test
	public void handleElevenMinuteLateStudentTest() {
		appointmentHandler.getCurrentStudentQueue().clear();
		appointmentHandler.generateRandomAppointments(1, FALSE, TRUE);
		assertNotEquals(AppConstants.STATUS_BANNED, appointmentHandler.getCurrentStudentQueue().peek().getStatus());
		assertEquals(0, appointmentHandler.getBanStudentQueue().size());
		appointmentHandler.handleAbsent();
		assertEquals(0, appointmentHandler.getCurrentStudentQueue().size());
		assertEquals(1, appointmentHandler.getBanStudentQueue().size());
		assertEquals(AppConstants.STATUS_BANNED, appointmentHandler.getBanStudentQueue().peek().getStatus());
		appointmentHandler.getBanStudentQueue().clear();
	}

	@Test
	public void handleFiveMinuteLateStudentTest() {
		appointmentHandler.getCurrentStudentQueue().clear();
		assertEquals(false, appointmentHandler.handleAbsent());
		appointmentHandler.generateRandomAppointments(2, FALSE, FALSE);
		Student student = appointmentHandler.getCurrentStudentQueue().peek();
		assertNotEquals(AppConstants.STATUS_ABSENT, student.getStatus());
		assertEquals(true, appointmentHandler.handleAbsent());
		assertEquals(2, appointmentHandler.getCurrentStudentQueue().size());
		assertNotEquals(student.getStudentID(), appointmentHandler.getCurrentStudentQueue().poll().getStudentID());
		assertEquals(student.getStudentID(), appointmentHandler.getCurrentStudentQueue().poll().getStudentID());
		assertEquals(AppConstants.STATUS_ABSENT, student.getStatus());
	}

	@Test
	public void handlePresentStudentTest() {
		appointmentHandler.getCurrentStudentQueue().clear();
		appointmentHandler.generateRandomAppointments(1, FALSE, TRUE);
		assertNotEquals(AppConstants.STATUS_PRESENT, appointmentHandler.getCurrentStudentQueue().peek().getStatus());
		assertEquals(0, appointmentHandler.getAttendedStudentQueue().size());
		assertEquals(true, appointmentHandler.startProcessToMarkStudentAsPresent());
		assertEquals(0, appointmentHandler.getCurrentStudentQueue().size());
		assertEquals(1, appointmentHandler.getAttendedStudentQueue().size());
		assertEquals(AppConstants.STATUS_PRESENT, appointmentHandler.getAttendedStudentQueue().peek().getStatus());
		appointmentHandler.getAttendedStudentQueue().clear();
	}

}
