package com.dtag.bm.process.service_order_management_process.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dtag.bm.process.service_order_management_process.model.ServiceOrder;

public interface ServiceOrderingRepository extends MongoRepository<ServiceOrder,String>{
	public ServiceOrder getById(String id);
	public ServiceOrder getByExternalId(String id);
	public void deleteById(String id);

}
