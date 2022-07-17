package com.saptris.restApi.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saptris.restApi.dto.AssetDTO;
import com.saptris.restApi.exception.AppException;
import com.saptris.restApi.exception.MissingDataException;
import com.saptris.restApi.service.AssetService;

@RestController
@RequestMapping("/assets")
@Validated //for path param validation
public class AssetsController {
	@Autowired
	public AssetService assetService;
	
	@GetMapping
	public ResponseEntity<ResponseStructure> getAssets() {
		return ResponseStructure.ok(assetService.getAllAssets());
	}
	@GetMapping("/{assetId}")
	public ResponseEntity<ResponseStructure> getAsset(@PathVariable @Pattern(regexp="[0-9a-f-]",message="asset.assetId.invalid") String assetId, @RequestParam(required=false,defaultValue="") String user) throws MissingDataException,AppException{
		if(!user.equals("me")) throw new MissingDataException("user is not me");
		//Object obj=null;
		//obj.toString();
		AssetDTO asset=assetService.getAsset(assetId);
		if(asset!=null)
			return ResponseStructure.ok(asset);
		else
			return ResponseStructure.noContent();
	}
	@PostMapping
	public ResponseEntity<String> createAsset(@RequestBody @Valid AssetDTO asset, Errors errors) {
		HttpHeaders responseHeaders= new HttpHeaders();
		responseHeaders.set("compressed", "false");
		System.out.println("local error from controller: "+errors.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(", "))); //this is not centralized. For all validations handler is made in ExceptionControllerAdvice
		String response=assetService.createAsset(asset);
		//return ResponseEntity.ok(response);
		return new ResponseEntity<String>(response,responseHeaders,HttpStatus.OK);
	}
	@PutMapping
	public String updateAsset() {
		return "updated asset";
	}
	@DeleteMapping
	public String deleteAsset() {
		return "deleted asset";
	}
}
