package com.dtag.bm.process.service_order_management_process.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServiceOrderEvent implements Serializable{

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 8546385061810070867L;

	public ServiceOrderEvent(ServiceOrder serviceOrder) {
		super();
		this.serviceOrder = serviceOrder;
	}

	@JsonProperty("serviceOrder")
	private ServiceOrder serviceOrder;

	public ServiceOrder getServiceOrder() {
		return serviceOrder;
	}

	public void setServiceOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	@Override
	public String toString() {
		return "ServiceOrderEvent [serviceOrder=" + serviceOrder + "]";
	}

}
