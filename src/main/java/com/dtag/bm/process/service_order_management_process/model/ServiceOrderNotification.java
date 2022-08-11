package com.dtag.bm.process.service_order_management_process.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	
	"event"
	
})

public class ServiceOrderNotification extends Notification implements Serializable{
	
	

	public ServiceOrderNotification(Object source, String eventId, String eventDate, String eventType,
			ServiceOrder event) {
		super(source, eventId, eventDate, eventType);
		this.event = event;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8546385061810070867L;

	@JsonProperty("event")
	private ServiceOrder event;

	public ServiceOrder getEvent() {
		return event;
	}

	public void setEvent(ServiceOrder event) {
		this.event = event;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ServiceOrderNotification [event=" + event + "]";
	}
	
	

	
}
