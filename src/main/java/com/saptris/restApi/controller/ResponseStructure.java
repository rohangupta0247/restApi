package com.saptris.restApi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;
import lombok.NonNull;

@Data
public class ResponseStructure {
	@NonNull
	public Double apiVersion;
	@NonNull
	public Integer code;
	@NonNull
	public String message;
	@NonNull
	public Object data;
	
	public static ResponseEntity<ResponseStructure> ok(Object data) {
		if(data==null ||
			(data instanceof List && ((List<?>) data).size()==0)) return noContent();
		ResponseStructure response= new ResponseStructure(1.0, 200, "success",data);
		return ResponseEntity.ok(response);
	}
	public static ResponseEntity<ResponseStructure> noContent() {
		return ResponseEntity.noContent().build();//204 cannot have response body
	}
	public static ResponseEntity<ResponseStructure> userError(String message) {
		ResponseStructure response = new ResponseStructure(1.0, 400, message,"");
		return new ResponseEntity<ResponseStructure>(response,HttpStatus.BAD_REQUEST);
	}
	public static ResponseEntity<ResponseStructure> internalServerError(String message) {
		ResponseStructure response = new ResponseStructure(1.0, 500, message,"");
		return new ResponseEntity<ResponseStructure>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
