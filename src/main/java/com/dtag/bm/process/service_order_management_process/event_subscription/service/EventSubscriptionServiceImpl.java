package com.dtag.bm.process.service_order_management_process.event_subscription.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dtag.bm.process.service_order_management_process.controller.ServiceOrderingController;
import com.dtag.bm.process.service_order_management_process.dao.ServiceOrderingRepository;
import com.dtag.bm.process.service_order_management_process.event_subscription.model.Hub;
import com.dtag.bm.process.service_order_management_process.model.ServiceOrderNotification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;

@Service
public class EventSubscriptionServiceImpl implements EventSubscriptionService , ApplicationListener<ServiceOrderNotification>{
	private static Logger logger = LoggerFactory.getLogger(ServiceOrderingController.class);
	@Autowired
	ServiceOrderingRepository orderingRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Hub createEvenSubscriptionsService(Hub eventSubscription) {
		
		// generate UUID and set
				UUID uuid = Generators.timeBasedGenerator().generate();
				eventSubscription.setId(uuid.toString());
		
		
		mongoTemplate.save(eventSubscription);
		
		return eventSubscription;
		
		 
	}

	@Override
	public List<Hub> getAllEvenSubscriptions() {
		
		return mongoTemplate.findAll(Hub.class);
		
	}
	
public Hub getEvenSubscriptions(String id) {
		
	Query query = new Query();
	query.addCriteria(Criteria.where("_id").is(id));
	
	return mongoTemplate.findOne(query, Hub.class);
		
	}

	@Override
	public Hub deleteEvenSubscriptions(String id) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		
		mongoTemplate.remove(query, Hub.class);
		
		return null;
	}

	@Override
	public void onApplicationEvent(ServiceOrderNotification event) {
		ServiceOrderNotification serviceOrderNotification = (ServiceOrderNotification) event;
		ObjectMapper mapper = new ObjectMapper();
        
		try {
			String jsonString = mapper.writeValueAsString(serviceOrderNotification);
			logger.info("Event "+jsonString);
			List<Hub> hub=getAllEvenSubscriptions();
			for(Hub hub2 : hub)
			{
				String callback = hub2.getCallback();
				logger.info("callback url :" +callback);
				//Logic to post event on callback url goes here

			}
			
			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	




	

}
