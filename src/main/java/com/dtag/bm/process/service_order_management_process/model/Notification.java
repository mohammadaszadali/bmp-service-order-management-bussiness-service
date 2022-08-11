package com.dtag.bm.process.service_order_management_process.model;

import java.io.Serializable;

import org.springframework.context.ApplicationEvent;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification extends ApplicationEvent implements Serializable{

	/*
	 * public Notification(Object source) { super(source); // TODO Auto-generated
	 * constructor stub }
	 */

	public Notification(Object source, String eventId, String eventDate, String eventType) {
		super(source);
		this.eventId = eventId;
		this.eventDate = eventDate;
		this.eventType = eventType;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8546385061810070867L;

	@JsonProperty("eventId")
	private String eventId;

	@JsonProperty("eventDate")
	private String eventDate;

	@JsonProperty("eventType")
	private String eventType;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
	
	
}
