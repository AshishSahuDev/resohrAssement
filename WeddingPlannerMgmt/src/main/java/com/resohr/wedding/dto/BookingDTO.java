package com.resohr.wedding.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingDTO {

	@NotNull(message = "Event ID is required")
	private Long eventID;

	@NotNull(message = "Vendor ID is required")
	private Long vendorID;
}
