package com.dtag.bm.process.service_order_management_process.service;

import java.util.List;

import com.dtag.bm.process.service_order_management_process.model.ServiceOrder;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ServiceOrderingService {

   public ServiceOrder createService(ServiceOrder order) throws JsonProcessingException;
   public ServiceOrder getServiceById(String Id);
   public List<ServiceOrder> getAllServices();
   public ServiceOrder updateCompleteServiceOrder(String id,ServiceOrder serviceOrder) throws JsonProcessingException;
   public ServiceOrder updateCompleteServiceOrderByExternalId(String id, ServiceOrder serviceOrder) throws JsonProcessingException;
   public List<ServiceOrder> getServiceByExternalId(String Id);
   public ServiceOrder updateCompleteServiceOrderFromStub(String externalId, String environmentContext, String serviceOrderId,
			String serviceInstanceId, String state, String uProfileId, String qualityProfile, String dnnlist);
}

