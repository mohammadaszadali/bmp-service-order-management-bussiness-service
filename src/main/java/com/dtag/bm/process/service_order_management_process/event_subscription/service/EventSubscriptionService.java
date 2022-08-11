package com.dtag.bm.process.service_order_management_process.event_subscription.service;

import java.util.List;

import com.dtag.bm.process.service_order_management_process.event_subscription.model.Hub;


public interface EventSubscriptionService {
	
	public Hub createEvenSubscriptionsService(Hub eventSubscription);
	public List<Hub> getAllEvenSubscriptions();
	public Hub deleteEvenSubscriptions(String id);

}
