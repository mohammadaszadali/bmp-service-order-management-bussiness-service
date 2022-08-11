package com.dtag.bm.process.service_order_management_process.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dtag.bm.process.service_order_management_process.dao.ServiceOrderingRepository;
import com.dtag.bm.process.service_order_management_process.model.Service;
import com.dtag.bm.process.service_order_management_process.model.ServiceOrder;
import com.dtag.bm.process.service_order_management_process.model.ServiceOrderItem;
import com.dtag.bm.process.service_order_management_process.model.ServiceOrderNotification;
import com.dtag.bm.process.service_order_management_process.utils.ServiceManagementServiceConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;

public class ServiceOrderingSrvImpl implements ServiceOrderingService, ApplicationEventPublisherAware {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoTemplate.class);

	@Autowired
	ServiceOrderingRepository orderingRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	private ApplicationEventPublisher publisher;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${publishStubMessage}")
	private String publishStubMessage;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;

	}

	@Override
	public ServiceOrder createService(ServiceOrder serviceOrder) throws JsonProcessingException {

		mongoTemplate.save(serviceOrder);

		UUID uuid = Generators.timeBasedGenerator().generate();
		long ids = uuid.node();
		String EventId = Long.toString(ids);
		String eventDate = LocalDateTime.now().toString();
		ObjectMapper mapper = new ObjectMapper();

		ServiceOrderNotification event = new ServiceOrderNotification(this, EventId, eventDate,
				ServiceManagementServiceConstants.CREATE_NOTIFICATION, serviceOrder);

		String jsonString = mapper.writeValueAsString(event);

		// publishing the event here
		publisher.publishEvent(event);

		return serviceOrder;

	}

	@Override
	public ServiceOrder getServiceById(String Id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id".trim()).is(Id.trim()));

		ServiceOrder serviceOrder = mongoTemplate.findOne(query, ServiceOrder.class);
		return serviceOrder;
	}

	public List<ServiceOrder> getServiceByExternalId(String externalId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("externalId".trim()).is(externalId.trim()));

		List<ServiceOrder> serviceOrder = mongoTemplate.find(query, ServiceOrder.class);
		return serviceOrder;
	}

	public ServiceOrder deleteById(String externalId) throws JsonProcessingException {

		Query query = new Query();
		query.addCriteria(Criteria.where("externalId".trim()).is(externalId.trim()));

		ServiceOrder serviceOrder = mongoTemplate.findOne(query, ServiceOrder.class);

		UUID uuid = Generators.timeBasedGenerator().generate();
		long ids = uuid.node();
		String EventId = Long.toString(ids);
		ObjectMapper mapper = new ObjectMapper();

		ServiceOrderNotification event = new ServiceOrderNotification(this, EventId, LocalDateTime.now().toString(),
				ServiceManagementServiceConstants.REMOVE_NOTIFICATION5, serviceOrder);

		String jsonString = mapper.writeValueAsString(event);

		// publishing the event here
		publisher.publishEvent(event);

		LOGGER.info(jsonString);

		return mongoTemplate.findAndRemove(query, ServiceOrder.class);

	}

	@Override
	public List<ServiceOrder> getAllServices() {

		return mongoTemplate.findAll(ServiceOrder.class);

	}

	@Override
	public ServiceOrder updateCompleteServiceOrder(String id, ServiceOrder serviceOrder)
			throws JsonProcessingException {
		serviceOrder.setId(id);
		mongoTemplate.save(serviceOrder);

		UUID uuid = Generators.timeBasedGenerator().generate();
		long ids = uuid.node();
		String EventId = Long.toString(ids);
		String eventDate = LocalDateTime.now().toString();
		ObjectMapper mapper = new ObjectMapper();

		ServiceOrderNotification event = new ServiceOrderNotification(this, EventId, eventDate,
				ServiceManagementServiceConstants.STATE_CHANGE_NOTIFICATION, serviceOrder);

		String jsonString = mapper.writeValueAsString(event);

		// publishing the event here
		publisher.publishEvent(event);

		return serviceOrder;
	}

	@Override
	public ServiceOrder updateCompleteServiceOrderByExternalId(String id, ServiceOrder serviceOrder)
			throws JsonProcessingException {

		Query query = new Query();
		query.addCriteria(Criteria.where("externalId").is(id));

		ServiceOrder order = mongoTemplate.findOne(query, ServiceOrder.class);
		String orderId = order.getId();

		serviceOrder.setId(orderId);
		serviceOrder.setExternalId(id);
		mongoTemplate.save(serviceOrder);

		UUID uuid = Generators.timeBasedGenerator().generate();
		long ids = uuid.node();
		String EventId = Long.toString(ids);
		String eventDate = LocalDateTime.now().toString();
		ObjectMapper mapper = new ObjectMapper();

		ServiceOrderNotification event = new ServiceOrderNotification(this, EventId, eventDate,
				ServiceManagementServiceConstants.STATE_CHANGE_NOTIFICATION, serviceOrder);

		String jsonString = mapper.writeValueAsString(event);

		// publishing the event here
		publisher.publishEvent(event);

		return serviceOrder;

	}
	
	/*Stub implementation started */
	
	public ServiceOrder createServiceFromStub(ServiceOrder serviceOrder) {
		// TODO Auto-generated method stub
		UUID uuid = Generators.timeBasedGenerator().generate();
		/*long ids = uuid.node();
		String id = Long.toString(ids);*/
		LOGGER.info("id is " + uuid.toString());
		serviceOrder.setId(uuid.toString());
		serviceOrder.setHref(new StringBuffer(ServiceManagementServiceConstants.URL).append(uuid).toString());
		serviceOrder.setState("Acknowledged");
		Date date = new Date();
		DateFormat formatter = new SimpleDateFormat(ServiceManagementServiceConstants.DATE_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone(ServiceManagementServiceConstants.TIMEZONE));
		serviceOrder.setOrderDate(formatter.format(date));
		List<ServiceOrderItem> items = new ArrayList<>();
		List<ServiceOrderItem> orderItems = serviceOrder.getOrderItem();
		for (ServiceOrderItem serviceOrderItem : orderItems) {
			Service service = serviceOrderItem.getService();
			service.setId(Generators.timeBasedGenerator().generate().toString());
			
		}
		mongoTemplate.save(serviceOrder);
		return serviceOrder;
	}

	@Override
	public ServiceOrder updateCompleteServiceOrderFromStub(String externalId, String environmentContext,String serviceOrderId,String serviceInstanceId,String state, String uProfileId, String qualityProfile, String dnnlist) {
		LOGGER.info(" request of externalId " + externalId + " serviceOrderId " + serviceOrderId + " serviceInstanceId " + serviceInstanceId + " state " + state + "profileId" + uProfileId + "qualityProfile" + qualityProfile + "dnnlist" + dnnlist); 
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(serviceInstanceId));
		LOGGER.info(" serviceInstanceId  " + serviceInstanceId );
		ServiceOrder order = mongoTemplate.findOne(query, ServiceOrder.class);
		if (externalId != null && serviceOrderId != null && !externalId.isEmpty() && !serviceOrderId.isEmpty() && serviceInstanceId != null && !serviceInstanceId.isEmpty()) {
			query.addCriteria(new Criteria().andOperator(Criteria.where("_id").is(serviceInstanceId).exists(true)
					.where("externalId").is(externalId)));
			order = mongoTemplate.findOne(query, ServiceOrder.class);
			List<ServiceOrderItem> orderItems = order.getOrderItem();
			for(ServiceOrderItem orderItem : orderItems) {
				if(null!=orderItem && !orderItem.getId().isEmpty() && orderItem.getId().equals(serviceOrderId)) {
					orderItem.setState(state);
				}
			}
			mongoTemplate.save(order);
		}else if ((null == serviceOrderId || serviceOrderId.trim().isEmpty()) && !serviceInstanceId.trim().isEmpty()) {
			order.setState(state);
			mongoTemplate.save(order);
		}
		String serviceOrderNotification = createStubONAPMessage(externalId, environmentContext,serviceOrderId,serviceInstanceId,state,uProfileId,qualityProfile,dnnlist);
		
		LOGGER.info("ONAP Stub notification message : " + serviceOrderNotification.toString());
		LOGGER.info("URL : " + publishStubMessage);

	
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(serviceOrderNotification.toString(), headers);
			ResponseEntity<String> response = restTemplate.postForEntity(publishStubMessage, entity, String.class);

			LOGGER.info("Response : " + response);
		return order;

	}
	
private String createStubONAPMessage(String externalId,String environmentContext, String serviceOrderId, String serviceInstanceId, String state, String uProfileId, String qualityProfile, String dnnlist) {
        
        JSONObject messageNotification = new JSONObject();
        
        try {

        messageNotification.put("messageName", "Message_1or74oa");
        messageNotification.put("businessKey", externalId);

        JSONObject varMapping = new JSONObject();

        Map<String, String> varValueTypeMap1 = new HashMap<String, String>();
        varValueTypeMap1.put("type", "String");
        varValueTypeMap1.put("value", externalId);
        varMapping.put("externalId", varValueTypeMap1);

        Map<String, String> varValueTypeMap2 = new HashMap<String, String>();
        varValueTypeMap2.put("type", "String");
        varValueTypeMap2.put("value", serviceOrderId);
        varMapping.put("serviceOrderId", varValueTypeMap2);
        
        Map<String, String> varValueTypeMap3 = new HashMap<String, String>();
        varValueTypeMap3.put("type", "String");
        varValueTypeMap3.put("value", state);
        varMapping.put("asyncOnapResponse", varValueTypeMap3);
        
        
                     Map<String, String> varValueTypeMap4 = new HashMap<String, String>();
                     varValueTypeMap4.put("type", "String");
                     varValueTypeMap4.put("value", serviceInstanceId);
                     varMapping.put("serviceInstanceId", varValueTypeMap4);
                     
                     Map<String, String> varValueTypeMap5 = new HashMap<String, String>();
                     varValueTypeMap5.put("type", "String");
                     varValueTypeMap5.put("value", environmentContext);
                     varMapping.put("environmentContext", varValueTypeMap5);
               
                     Map<String, String> varValueTypeMap6 = new HashMap<String, String>();
                     varValueTypeMap6.put("type", "String");
                     varValueTypeMap6.put("value", uProfileId);
                     varMapping.put("uProfileId", varValueTypeMap6);
                     
                     Map<String, String> varValueTypeMap7 = new HashMap<String, String>();
                     varValueTypeMap7.put("type", "String");
                     varValueTypeMap7.put("value", qualityProfile);
                     varMapping.put("uQualityProfile", varValueTypeMap7);
                     
                     Map<String, String> varValueTypeMap8 = new HashMap<String, String>();
                     varValueTypeMap8.put("type", "String");
                     varValueTypeMap8.put("value", dnnlist);
                     varMapping.put("uDnnlist", varValueTypeMap8);

        //Correlation Keys 
        
        JSONObject varCoMapping = new JSONObject();

        Map<String, String> varCoValueTypeMap1 = new HashMap<String, String>();
        varCoValueTypeMap1.put("type", "String");
        varCoValueTypeMap1.put("value", serviceOrderId);
        varCoMapping.put("serviceOrderId", varCoValueTypeMap1);
        
        
        messageNotification.put("correlationKeys", varCoMapping);

        messageNotification.put("processVariables", varMapping);        
        
        
        messageNotification.put("resultEnabled", true);             
        

        LOGGER.info("Service Order Notification : " + messageNotification.toString());
        return messageNotification.toString();
        
        } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
        }
        return messageNotification.toString();
  }

/*Stub implementation ended */
	

}
