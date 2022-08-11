package com.dtag.bm.process.service_order_management_process.event_subscription.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
	"id",
	"callback",	
	"query"
	
	
})
@Document(collection = "HUB")
public class Hub implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7177109032668900983L;

	@JsonProperty("callback")
	private String callback;
	
	//@JsonIgnore
	//@JsonIgnoreProperties
	@JsonProperty("id")
	private String id;
	
	
	
	@JsonProperty("query")
	private String query;

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}
	
	public String getQuery() {
		return query;
	}


	public void setQuery(String query) {
		this.query = query;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	public String getId() {
		return id;
	}
@ApiModelProperty(hidden=true)
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Hub [callback=" + callback + ", id=" + id + ", query=" + query + ", getCallback()=" + getCallback()
				+ ", getQuery()=" + getQuery() + ", getId()=" + getId() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	
	
	
	
	
}
