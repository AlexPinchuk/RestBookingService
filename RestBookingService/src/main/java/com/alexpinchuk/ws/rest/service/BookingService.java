package com.alexpinchuk.ws.rest.service;

import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alexpinchuk.ws.rest.controller.SñheduleMaker;
import com.alexpinchuk.ws.rest.model.BookingInformation;
import com.alexpinchuk.ws.rest.model.Meeting;
import com.sun.jersey.core.provider.EntityHolder;

@Path("/bookingService")
public class BookingService {
	
	@POST
	@Path("/schedule")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Map<String, Set<Meeting>> makeBookingShedule(EntityHolder<BookingInformation> entityHolder){	
		try{
			if(entityHolder.hasEntity()){
				BookingInformation bookingInformation = entityHolder.getEntity();
				SñheduleMaker sñheduleMaker = new SñheduleMaker();
				
				return sñheduleMaker.makeSchedule(bookingInformation);
			}
		} catch(NullPointerException e){
			e.printStackTrace();
		} 
		
		return null;
	}
}
