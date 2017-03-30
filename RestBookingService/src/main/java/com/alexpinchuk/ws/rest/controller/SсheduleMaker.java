package com.alexpinchuk.ws.rest.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.alexpinchuk.ws.rest.model.BookingInformation;
import com.alexpinchuk.ws.rest.model.BookingRequest;
import com.alexpinchuk.ws.rest.model.Meeting;
import com.alexpinchuk.ws.rest.model.OfficeTimetable;

public class SñheduleMaker {

	private DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HHmm");
	private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
	private DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
	
	private ScheduleFactory scheduleFactory = new ScheduleFactory();
			
	public Map<String, Set<Meeting>> makeSchedule(BookingInformation bookingInformation){
        Map<String, Set<Meeting>> sñhedule = new TreeMap<String, Set<Meeting>>();
        
		Set<BookingRequest> bookingRequests = scheduleFactory.createRequestSet();
		bookingRequests.addAll(bookingInformation.getBookingRequests());	

		Iterator<BookingRequest> iterator = bookingRequests.iterator();
		while(iterator.hasNext()){
			BookingRequest request = iterator.next();
			
			String employeeId = request.getEmployeeId();
			DateTime meetingStartTime = new DateTime(dateTimeFormatter.parseDateTime(request.getMeetingStartTime()));
			DateTime meetingFinishTime = new DateTime(meetingStartTime.plusHours(Integer.valueOf(request.getMeetingDuration())));
			
			if(isOutOfOfficeTimeTable(meetingStartTime, meetingFinishTime, bookingInformation.getOfficeTimetable())){
				iterator.remove();
				continue;
			}
				
			Set<Meeting> meetingsForDate = sñhedule.get(dateFormatter.print(meetingStartTime));
			
			if(meetingsForDate == null){
				meetingsForDate = scheduleFactory.createMeetingSet();
				meetingsForDate.add(scheduleFactory.createMeeting(employeeId, meetingStartTime, meetingFinishTime));
				sñhedule.put(dateFormatter.print(meetingStartTime), meetingsForDate);
				continue;
			}
				
			for(Meeting meetingForDate: meetingsForDate){
				if(isMeetingOverlap(meetingStartTime, meetingFinishTime, 
									meetingForDate.seeMeetingStartTime(), 
									meetingForDate.seeMeetingFinishTime())){
					
					iterator.remove();
					continue;
				} else meetingsForDate.add(scheduleFactory.createMeeting(employeeId, meetingStartTime, meetingFinishTime));		
			}
		}
		return sñhedule;
	}
		
	public boolean isOutOfOfficeTimeTable(DateTime meetingStartTime, DateTime meetingFinishTime, OfficeTimetable timetable){
		LocalTime officeStartLocalTime = new LocalTime(
				timeFormatter.parseLocalTime(timetable.getOfficeStartTime()));
		LocalTime officeFinishLocalTime = new LocalTime(
				timeFormatter.parseLocalTime(timetable.getOfficeFinishTime()));		
		
		return officeTimeTableCheck(meetingStartTime.toLocalTime(), meetingFinishTime.toLocalTime(), officeStartLocalTime, officeFinishLocalTime);
	}
	
	public boolean isMeetingOverlap(DateTime startTime1, DateTime finishTime1, DateTime startTime2, DateTime finishTime2){
		return (startTime1.isBefore(startTime2)&&finishTime1.isAfter(startTime2))||
				(startTime2.isBefore(startTime1)&&finishTime2.isAfter(startTime1))||
				(startTime1.isEqual(startTime2)&&finishTime1.isEqual(finishTime2));
	}

	public boolean officeTimeTableCheck(LocalTime meetingStartTime, LocalTime meetingFinishTime, LocalTime officeStartTime, LocalTime officeFinishTime){
		return meetingStartTime.isBefore(officeStartTime) 
				|| meetingFinishTime.isAfter(officeFinishTime) 
				|| meetingStartTime.isAfter(officeFinishTime) 
				|| meetingFinishTime.isBefore(officeStartTime);
	}
	
}
