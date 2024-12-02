package com.resohr.wedding.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentDTO {

	@NotNull(message = "Client ID is required")
	private Long clientID;

	@NotNull(message = "Amount is required")
	@Min(value = 1, message = "Amount must be greater than zero")
	private Double amount;
}
