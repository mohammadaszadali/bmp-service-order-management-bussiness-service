package com.dtag.bm.process.service_order_management_process.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
	"code",
	"message",
	"reason",
	"action",
	"errorCode",
	"source"
})
public class Message implements Serializable{
	
	private static final long serialVersionUID = 660768982080207843L;

	@JsonProperty("code")
	private String code;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("reason")
	private String reason;
	
	@JsonProperty("action")	
	private String action;
	
	@JsonProperty("errorCode")
	private String errorCode;
	
	@JsonProperty("source")
	private String source;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "Message [code=" + code + ", message=" + message + ", reason=" + reason + ", action=" + action
				+ ", errorCode=" + errorCode + ", source=" + source + "]";
	}	
	
}
