package com.dtag.bm.process.service_order_management_process.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtag.bm.process.service_order_management_process.dao.ServiceOrderingRepository;
import com.dtag.bm.process.service_order_management_process.model.ServiceOrder;
import com.dtag.bm.process.service_order_management_process.service.ServiceOrderingSrvImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/serviceOrderManagement/v1")
public class ServiceOrderingController {
	private static Logger logger = LoggerFactory.getLogger(ServiceOrderingController.class);
	@Autowired
	private ServiceOrderingSrvImpl service;

	@Autowired
	ServiceOrderingRepository orderingRepository;

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
	@ApiOperation(value = "create new Service", notes = "create new Service")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created new Service order"),
			@ApiResponse(code = 201, message = "Successfully created new Service order"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/serviceOrder")
	public ResponseEntity<?> createService(@RequestBody ServiceOrder serviceOrder)
			throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {

			ServiceOrder order = service.createService(serviceOrder);
			String serviceOrderId = order.getId();
			responseEntity = new ResponseEntity<>(serviceOrderId, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}

	/**
	 * This will update existing Service Orders entirely by service order id and
	 * returning order id as response
	 * 
	 * @return
	 * @throws com.fasterxml.jackson.core.JsonProcessingException
	 * 
	 */
	@ApiOperation(value = "update existing Service order", notes = "update existing Service")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated existing Service order"),
			@ApiResponse(code = 201, message = "Successfully updated existing Service order"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping("/serviceOrder/{id}")
	public ResponseEntity<?> updateService(@PathVariable(value = "id") String id,
			@RequestBody ServiceOrder serviceOrder) throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {

			ServiceOrder order = service.updateCompleteServiceOrder(id, serviceOrder);
			String serviceOrderId = order.getId();
			responseEntity = new ResponseEntity<>(order, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}

	/**
	 * This will update existing Service Orders entirely by service order ExternalId
	 * and returning order id as response
	 * 
	 * @return
	 * @throws com.fasterxml.jackson.core.JsonProcessingException
	 * 
	 */
	@ApiOperation(value = "update existing Service order", notes = "update existing Service")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated existing Service order"),
			@ApiResponse(code = 201, message = "Successfully updated existing Service order"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping("/serviceOrder/ExternalId/{ExternalId}")
	public ResponseEntity<?> updateServiceByExternalId(@PathVariable(value = "ExternalId") String ExternalId,
			@RequestBody ServiceOrder serviceOrder) throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {

			ServiceOrder order = service.updateCompleteServiceOrderByExternalId(ExternalId, serviceOrder);
			String serviceOrderId = order.getId();
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
	@ApiOperation(value = "fetch  ServiceOrderById", notes = "fetch  ServiceOrderById")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully fetch  ServiceOrderById"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/serviceOrder/{id}")
	public ResponseEntity<?> getOderById(@PathVariable(value = "id") String Id)
			throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			ServiceOrder serviceOrder = service.getServiceById(Id);
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

	/**
	 * This will fetch Service Order By External Id
	 * 
	 * @return
	 * @throws com.fasterxml.jackson.core.JsonProcessingException
	 * 
	 */
	@ApiOperation(value = "fetch  ServiceOrderByExternalId", notes = "fetch  Service Order By External Id")

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully fetch  Service Order By External Id"),

			@ApiResponse(code = 500, message = "Internal Server Error") })

	@GetMapping("/serviceOrder/getByExternalId/{externalId}")
	public ResponseEntity<?> getServiceByExternalId(@PathVariable(value = "externalId") String externalId)
			throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			List<ServiceOrder> serviceOrder = service.getServiceByExternalId(externalId);
			// String id = serviceOrder.getExternalId();
			if (serviceOrder != null) {
				responseEntity = new ResponseEntity<>(serviceOrder, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}

	/**
	 * This will fetch All Services
	 * 
	 * @return
	 * @throws com.fasterxml.jackson.core.JsonProcessingException
	 * 
	 */
	@ApiOperation(value = "fetch All Services", notes = "fetch All Services")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully fetched All Services "),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/serviceOrder/getAllServices")
	public ResponseEntity<?> getAllServices() throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {

			List<ServiceOrder> serviceOrder = service.getAllServices();

			if (serviceOrder != null) {
				responseEntity = new ResponseEntity<>(serviceOrder, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}

	/**
	 * This will delete ServiceOrderById
	 * 
	 * @return
	 * @throws com.fasterxml.jackson.core.JsonProcessingException
	 * 
	 */
	@ApiOperation(value = "delete  ServiceOrderById", notes = "delete  ServiceOrderById")
	@DeleteMapping("/serviceOrder/{externalId}")
	public ResponseEntity<?> deleteOderById(@PathVariable(value = "externalId") String externalId)
			throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		try {

			ServiceOrder response = service.deleteById(externalId);
			if (response != null) {
				responseEntity = new ResponseEntity<>(" Successfully deleted  ServiceOrder ", HttpStatus.OK);

			} else {
				responseEntity = new ResponseEntity<>("ServiceOrder not  found ", HttpStatus.NOT_FOUND);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}
	
	/**
	 * This will create New Service Orders and returning order id as response
	 * 
	 * @return
	 * @throws com.fasterxml.jackson.core.JsonProcessingException
	 * 
	 */
	@ApiOperation(value = "create new ServiceFromStub", notes = "create new ServiceFromStub")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created new ServiceorderFromStub"),
			@ApiResponse(code = 201, message = "Successfully created new ServiceorderFromStub"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping("/serviceOrderFromStub")
	public ResponseEntity<?> createServiceFromStub(@RequestBody ServiceOrder serviceOrder)
			throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {

			ServiceOrder orderService = service.createServiceFromStub(serviceOrder);
			String serviceOrderId = orderService.getId();
			responseEntity = new ResponseEntity<>(orderService, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}

	/**
	 * This will update existing Service OrdersFromStub entirely by service order id
	 * and returning order id as response
	 * 
	 * @return
	 * @throws com.fasterxml.jackson.core.JsonProcessingException
	 * 
	 */
	@ApiOperation(value = "update existing ServiceOrderFromStub", notes = "update existing ServiceOrderFromStub")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated existing ServiceOrderFromStub"),
			@ApiResponse(code = 201, message = "Successfully updated existing ServiceOrderFromStub"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping("/serviceOrderFromStub/update")
	public ResponseEntity<?> updateServiceFromStub(@RequestParam(value="externalId",required =true) String externalId,
			@RequestParam(value = "serviceOrderId",required = true) String serviceOrderId, 
			@RequestParam(value = "Environment-Context (SNSSAI Details)" ,required=true) String environmentContext,
			@RequestParam(value = "state",required = true) String state ,
			@RequestParam(value = "UDMProfileID",required = false) String profileId ,
			@RequestParam(value = "QualityProfile",required = false) String QualityProfile ,
			@RequestParam(value = "dnnlist",required = false) String dnnlist) throws com.fasterxml.jackson.core.JsonProcessingException {
		ResponseEntity<?> responseEntity = null;
		final String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		logger.info("Environment-Context (SNSSAI Details) : "+environmentContext);
		logger.info("UDMProfileID : "+profileId);
		
		try {

			ServiceOrder order = service.updateCompleteServiceOrderFromStub(externalId,environmentContext, serviceOrderId,serviceOrderId,state,profileId,QualityProfile,dnnlist);
			responseEntity = new ResponseEntity<>(order, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;

	}

}
