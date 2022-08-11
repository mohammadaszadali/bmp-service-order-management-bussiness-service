package com.dtag.bm.process.service_order_management_process.event_subscription.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtag.bm.process.service_order_management_process.dao.ServiceOrderingRepository;
import com.dtag.bm.process.service_order_management_process.event_subscription.model.Hub;
import com.dtag.bm.process.service_order_management_process.event_subscription.service.EventSubscriptionServiceImpl;
import com.dtag.bm.process.service_order_management_process.model.ServiceOrderRequest;
import com.dtag.bm.process.service_order_management_process.service.ServiceOrderRequestServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")        
@RestController
@RequestMapping("/serviceOrderManagement/v1")
public class EventSubscriptionController {
	private static Logger logger = LoggerFactory.getLogger(EventSubscriptionController.class);
	
	@Autowired
	private EventSubscriptionServiceImpl service;
	
	@Autowired
	ServiceOrderingRepository orderingRepository;
	
	//com.dtag.bm.process.order_management_process.service.ServiceOrderRequestServiceImpl
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	private final String className = this.getClass().getSimpleName();

	/**
	 * This will create New Service Orders and returning order id as response
	 * 
	 * @return
	 * @throws com.fasterxml.jackson.core.JsonProcessingException
	 * 
	 */
	@ApiOperation(value = "create new EventSubscription", notes = "create new EventSubscription")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created new EventSubscription"),
			@ApiResponse(code = 201, message = "Successfully created new EventSubscription"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/hub")
	public ResponseEntity<?> createService(@RequestBody Hub hubRequest)
			throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			
			Hub hub = service.createEvenSubscriptionsService(hubRequest);

			responseEntity = new ResponseEntity<>(hub, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}

	
	/**
	 * This will fetch ServiceOrderById
	 * 
	 * @return
	 * @throws com.fasterxml.jackson.core.JsonProcessingException
	 * 
	 */
	@ApiOperation(value = "fetch  all EvenSubscription", notes = "fetch all EvenSubscription")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully fetched all   EvenSubscription"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/hub")
	public ResponseEntity<?> getListOfEvenSubscription()
			throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			
			List<Hub>  hubList = service.getAllEvenSubscriptions();
			if (hubList != null) {
				responseEntity = new ResponseEntity<>(hubList, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>("EvenSubscription not found.", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}
	
	
	
	/**
	 * This will fetch ServiceOrderById
	 * 
	 * @return
	 * @throws com.fasterxml.jackson.core.JsonProcessingException
	 * 
	 */
	@ApiOperation(value = "delete  EvenSubscriptionById", notes = "delete  EvenSubscriptionById")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted  EvenSubscriptionById"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@DeleteMapping("/hub/{id}")
	public ResponseEntity<?> deleteEvenSubscriptionById(@PathVariable(value = "id") String Id)
			throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			
			Hub  hubRequest = service.deleteEvenSubscriptions(Id);
		
				responseEntity = new ResponseEntity<>("EvenSubscription deleted", HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}

	
}
