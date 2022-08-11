package com.dtag.bm.process.service_order_management_process.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"name",
	"valueType",
	"value"		
})
public class ServiceCharacteristic implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1913354755927075411L;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("valueType")
	private String valueType;
	
	@JsonProperty("value")
	private Value value;
	
/*	@JsonProperty("_id")
	private Value _id;
*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
	

	/*public Value get_id() {
		return _id;
	}

	public void set_id(Value _id) {
		this._id = _id;
	}
*/
	@Override
	public String toString() {
		return "ServiceCharacteristic [name=" + name + ", valueType=" + valueType + ", value=" + value 
				+ "]";
	}


	
	

}
