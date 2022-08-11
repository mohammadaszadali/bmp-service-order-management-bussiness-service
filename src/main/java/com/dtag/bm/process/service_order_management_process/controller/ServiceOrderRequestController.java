package com.dtag.bm.process.service_order_management_process.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtag.bm.process.service_order_management_process.dao.ServiceOrderingRepository;
import com.dtag.bm.process.service_order_management_process.model.ServiceOrderRequest;
import com.dtag.bm.process.service_order_management_process.service.ServiceOrderRequestServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")        
@RestController
@RequestMapping("/serviceOrderManagement/v1")
public class ServiceOrderRequestController {
	private static Logger logger = LoggerFactory.getLogger(ServiceOrderRequestController.class);
	
	@Autowired
	private ServiceOrderRequestServiceImpl service;
	
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
	@ApiOperation(value = "create new ServiceOrderRequest", notes = "create new Service")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created new Service order"),
			@ApiResponse(code = 201, message = "Successfully created new Service order"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/serviceOrderRequest")
	public ResponseEntity<?> createService(@RequestBody List<ServiceOrderRequest> serviceOrder)
			throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {

			List<ServiceOrderRequest> order = service.createServiceOrderRequestService(serviceOrder);
			logger.info("response saved in mongo " + order);
		//	String serviceOrderId = order.getExternalId();
			responseEntity = new ResponseEntity<>(order, HttpStatus.CREATED);
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
	@ApiOperation(value = "fetch  ServiceOrderRequestByExternalId", notes = "fetch  ServiceOrderRequestByExternalId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully fetch  ServiceOrderRequestByExternalId"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/serviceOrderRequest/{Externald}")
	public ResponseEntity<?> getOderById(@PathVariable(value = "Externald") String Id)
			throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			
			List<ServiceOrderRequest>  serviceOrder = service.getServiceOrderRequestServiceByExternalId(Id);
			if (serviceOrder != null) {
				responseEntity = new ResponseEntity<>(serviceOrder, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>("Service not found.", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}
	

	
}
