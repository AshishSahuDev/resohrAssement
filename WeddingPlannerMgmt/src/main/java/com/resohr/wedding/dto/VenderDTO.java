package com.resohr.wedding.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VenderDTO {

	@NotBlank(message = "Name is required")
	@Size(max = 100, message = "Vendor name cannot be more than 100 characters")
	private String name;

	@NotBlank(message = "Service type is required")
	@Size(max = 50, message = "Service type cannot be more than 50 characters")
	private String serviceType;

	@NotNull(message = "Service price is required")
    @Min(value = 0, message = "Service price must be non-negative")
    private Double servicePrice;
	
	@NotNull(message = "Contact number is required")
	@Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
	private Double contactNumber;
}
