package com.dtag.bm.process.service_order_management_process.service;

import java.util.List;

import com.dtag.bm.process.service_order_management_process.model.ServiceOrderRequest;

public interface ServiceOrderRequestService {
	
	public List<ServiceOrderRequest> createServiceOrderRequestService(List<ServiceOrderRequest> order);
	public List<ServiceOrderRequest> getServiceOrderRequestServiceByExternalId(String Id);

}
