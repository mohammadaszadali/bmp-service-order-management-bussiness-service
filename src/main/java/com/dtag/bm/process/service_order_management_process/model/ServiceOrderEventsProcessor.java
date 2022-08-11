package com.dtag.bm.process.service_order_management_process.model;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.MongoTemplate;

public class ServiceOrderEventsProcessor implements Serializable,ApplicationListener<ServiceOrderNotification>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8546385061810070867L;
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoTemplate.class);

	@Override
	public void onApplicationEvent(ServiceOrderNotification event) {
		
		ServiceOrderNotification orderEvent = event;
		LOGGER.info("Listning event");
		
		
	}

	

	
	
	
	
	
	
}
