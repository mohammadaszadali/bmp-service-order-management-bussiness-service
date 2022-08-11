package com.dtag.bm.process.service_order_management_process.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.dtag.bm.process.service_order_management_process.controller.ServiceOrderingController;
import com.dtag.bm.process.service_order_management_process.dao.ServiceOrderingRepository;
import com.dtag.bm.process.service_order_management_process.model.ServiceOrderRequest;

@Service
public class ServiceOrderRequestServiceImpl implements ServiceOrderRequestService{
	private static Logger logger = LoggerFactory.getLogger(ServiceOrderingController.class);
	@Autowired
	ServiceOrderingRepository orderingRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	@Override
	public List<ServiceOrderRequest> createServiceOrderRequestService(List<ServiceOrderRequest> order) {
		
		List<ServiceOrderRequest> list = new ArrayList();
		list.addAll(order);
		
		mongoTemplate.insert(order, ServiceOrderRequest.class);
		
		return order;
	}
	
	@Override
	public List<ServiceOrderRequest> getServiceOrderRequestServiceByExternalId(String Id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("ExternalId").is(Id));
		
		return mongoTemplate.find(query, ServiceOrderRequest.class);
		
	}




	

}
