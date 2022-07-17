package com.saptris.restApi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AssetDTO {
	@NotBlank
	String assetId;
	@NotBlank
	String taxonomyId;
	@DateTimeFormat
	LocalDate creationDate;
	LocalDateTime createdOn;
	@NotBlank
	String createdBy;
	LocalDateTime updatedOn;
	String updatedBy;
	@Future(message="effective end date should be a future date for active records")
	LocalDateTime effectiveEndDate;
}

/*
{
"assetId":"",
"taxonomyId":"taxo123",
"createdOn":"today",
"createdBy":"me",
"updatedOn":"",
"updatedBy":"",
"effectiveEndDate":"future"
}
*/