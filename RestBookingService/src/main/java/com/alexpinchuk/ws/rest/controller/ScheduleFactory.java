package com.alexpinchuk.ws.rest.controller;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.joda.time.DateTime;

import com.alexpinchuk.ws.rest.model.BookingRequest;
import com.alexpinchuk.ws.rest.model.Meeting;

public class ScheduleFactory {
	
	public Meeting createMeeting(String employeeId, DateTime meetingStartTime, DateTime meetingFinishTime){

		Meeting meeting = new Meeting();
		
		meeting.setEmployeeId(employeeId);
		meeting.setMeetingStartTime(meetingStartTime);
		meeting.setMeetingFinishTime(meetingFinishTime);
		
		return meeting;
	}
	
	public Set<Meeting> createMeetingSet(){
		return new TreeSet<Meeting>(new Comparator<Meeting>(){
		    public int compare(Meeting m1, Meeting m2) {
		        return m1.getMeetingStartTime().compareTo(m2.getMeetingStartTime());
		    }
		});
	}
	
	public Set<BookingRequest> createRequestSet(){
		return new TreeSet<BookingRequest>(new Comparator<BookingRequest>(){
			public int compare(BookingRequest o1, BookingRequest o2) {
				return o1.getRequestSubmissionTime().compareTo(o2.getRequestSubmissionTime());
			}
		});
	}
}
