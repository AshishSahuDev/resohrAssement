package com.resohr.wedding.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientDTO {

	@NotBlank(message = "Name is required")
	@Size(max = 100, message = "Name cannot be more than 100 characters")
	private String name;

	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	private String phoneNumber;

	@NotBlank(message = "Address is required")
	@Size(max = 200, message = "Address cannot be more than 200 characters")
	private String address;

	@NotNull(message = "Budget is required")
	@Min(value = 1000, message = "Budget must be at least 1000")
	private Double budget;
}
