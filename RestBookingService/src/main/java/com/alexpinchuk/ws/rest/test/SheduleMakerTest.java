package com.alexpinchuk.ws.rest.test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.alexpinchuk.ws.rest.controller.ScheduleFactory;
import com.alexpinchuk.ws.rest.controller.SñheduleMaker;
import com.alexpinchuk.ws.rest.model.BookingInformation;
import com.alexpinchuk.ws.rest.model.BookingRequest;
import com.alexpinchuk.ws.rest.model.Meeting;
import com.alexpinchuk.ws.rest.model.OfficeTimetable;

public class SheduleMakerTest {

	private SñheduleMaker scheduleMaker;
	private ScheduleFactory scheduleFactory; 
	
	@Before
	public void setUp(){
		scheduleMaker = new SñheduleMaker();
		scheduleFactory = new ScheduleFactory();
	}
	
	@Test
	public void shouldBeTrueIfMeetingOverlapsAnotherOne() {
		//given
		DateTime startTime1 = new DateTime(2017, 03, 26, 9, 0);
		DateTime finishTime1 = new DateTime(2017, 03, 26, 12, 0);
		DateTime startTime2 = new DateTime(2017, 03, 26, 10, 30);
		DateTime finishTime2 = new DateTime(2017, 03, 26, 13, 0);	
		
		//when
		boolean result = scheduleMaker.isMeetingOverlap(startTime1, finishTime1, startTime2, finishTime2);
		
		//then
		assertEquals(true, result);
	}
	
	@Test
	public void shouldBeTrueIfMeetingIsOutOfOfficeTimeTable(){
		//given
		LocalTime meetingStartTime = new LocalTime(9, 0, 0);
		LocalTime meetingFinishTime = new LocalTime(12, 0, 0);
		LocalTime officeStartTime = new LocalTime(10, 0, 0);
		LocalTime officeFinishTime = new LocalTime(17, 0, 0);
		
		//when
		boolean result = scheduleMaker.officeTimeTableCheck(meetingStartTime, meetingFinishTime, officeStartTime, officeFinishTime);

		//then
		assertEquals(true, result);
	}

	@Test
	public void shouldCreateSchedule(){
		//given
		BookingInformation bookingInformation = createBookinInformation();
        Map<String, Set<Meeting>> expectedSñhedule = createExpectedShedule();
        
        //when
        Map<String, Set<Meeting>> resultSchedule = scheduleMaker.makeSchedule(bookingInformation);
        
        //then
		assertEquals(expectedSñhedule, resultSchedule);
	}
	
	public Map<String, Set<Meeting>> createExpectedShedule(){
        Map<String, Set<Meeting>> sñhedule = new TreeMap<String, Set<Meeting>>();
        Set<Meeting> meetings1 = scheduleFactory.createMeetingSet();
        Set<Meeting> meetings2 = scheduleFactory.createMeetingSet();

        meetings1.add(scheduleFactory.createMeeting("EMP002", new DateTime(2011, 3, 21, 9, 0), new DateTime(2011, 3, 22, 11, 0)));
        meetings2.add(scheduleFactory.createMeeting("EMP003", new DateTime(2011, 3, 22, 14, 0), new DateTime(2011, 3, 22, 16, 0)));
        meetings2.add(scheduleFactory.createMeeting("EMP004", new DateTime(2011, 3, 22, 16, 0), new DateTime(2011, 3, 22, 17, 0)));

        sñhedule.put("2011-03-21", meetings1);
        sñhedule.put("2011-03-22", meetings2);
        
        return sñhedule;

	}
	
	public BookingInformation createBookinInformation(){
		Set<BookingRequest> requests = new HashSet<BookingRequest>();
		requests.add(createBookingRequest("2011-03-17 10:17:06", "EMP001", "2011-03-21 09:00", "2"));
		requests.add(createBookingRequest("2011-03-16 12:34:56", "EMP002", "2011-03-21 09:00", "2"));
		requests.add(createBookingRequest("2011-03-16 09:28:23", "EMP003", "2011-03-22 14:00", "2"));
		requests.add(createBookingRequest("2011-03-17 11:23:45", "EMP004", "2011-03-22 16:00", "1"));
		requests.add(createBookingRequest("2011-03-15 17:29:12", "EMP005", "2011-03-21 16:00", "3"));

		OfficeTimetable timetable = new OfficeTimetable();
		timetable.setOfficeStartTime("0900");
		timetable.setOfficeFinishTime("1730");
		
		BookingInformation bookingInformation = new BookingInformation();
		bookingInformation.setBookingRequests(requests);
		bookingInformation.setOfficeTimetable(timetable);
		
		return bookingInformation;
	}
	
	public BookingRequest createBookingRequest(String submissionTime, String id, String startTime, String duration){
		BookingRequest request = new BookingRequest();
		request.setEmployeeId(id);
		request.setMeetingDuration(duration);
		request.setMeetingStartTime(startTime);
		request.setRequestSubmissionTime(submissionTime);
		
		return request;
	}
	
}
