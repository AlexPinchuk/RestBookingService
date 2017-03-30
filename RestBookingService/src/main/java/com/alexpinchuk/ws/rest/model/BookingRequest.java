package com.alexpinchuk.ws.rest.model;

public class BookingRequest {

	private String requestSubmissionTime;
	private String employeeId;
	private String meetingStartTime;
	private String meetingDuration;
	
	public String getRequestSubmissionTime() {
		return requestSubmissionTime;
	}
	public void setRequestSubmissionTime(String requestSubmissionTime) {
		this.requestSubmissionTime = requestSubmissionTime;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getMeetingStartTime() {
		return meetingStartTime;
	}
	public void setMeetingStartTime(String meetingStartTime) {
		this.meetingStartTime = meetingStartTime;
	}
	public String getMeetingDuration() {
		return meetingDuration;
	}
	public void setMeetingDuration(String meetingDuration) {
		this.meetingDuration = meetingDuration;
	}
	
	
}
