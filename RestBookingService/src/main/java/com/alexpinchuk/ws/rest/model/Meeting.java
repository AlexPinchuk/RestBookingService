package com.alexpinchuk.ws.rest.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class Meeting {

	private String employeeId;
	private DateTime meetingStartTime;
	private DateTime meetingFinishTime;
	
	public String getEmployeeId() {
		return employeeId;
	}	
	public String getMeetingStartTime() {
		return DateTimeFormat.forPattern("HH:mm").print(meetingStartTime);
	}
	public String getMeetingFinishTime() {
		return DateTimeFormat.forPattern("HH:mm").print(meetingFinishTime);
	}
	
	public DateTime seeMeetingStartTime() {
		return meetingStartTime;
	}
	public DateTime seeMeetingFinishTime() {
		return meetingFinishTime;
	}
	
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setMeetingStartTime(DateTime meetingStartTime) {
		this.meetingStartTime = meetingStartTime;
	}

	public void setMeetingFinishTime(DateTime meetingFinishTime) {
		this.meetingFinishTime = meetingFinishTime;
	}

}
