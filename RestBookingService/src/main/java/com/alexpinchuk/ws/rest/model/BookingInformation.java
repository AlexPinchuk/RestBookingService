package com.alexpinchuk.ws.rest.model;

import java.util.Set;

public class BookingInformation {

	private OfficeTimetable officeTimetable;
	private Set<BookingRequest> bookingRequests = null;
	
	public OfficeTimetable getOfficeTimetable() {
		return officeTimetable;
	}
	public void setOfficeTimetable(OfficeTimetable officeTimetable) {
		this.officeTimetable = officeTimetable;
	}
	public Set<BookingRequest> getBookingRequests() {
		return bookingRequests;
	}
	public void setBookingRequests(Set<BookingRequest> bookingRequests) {
		this.bookingRequests = bookingRequests;
	}
	
}
