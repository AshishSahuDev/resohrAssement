package com.resohr.wedding.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EventDTO {

	@NotBlank(message = "Event name is required")
	@Size(max = 100, message = "Event name cannot be more than 100 characters")
	private String eventName;

	@NotNull(message = "Event date is required")
	@Future(message = "Event date must be in the future")
	private LocalDate eventDate;

	@NotNull(message = "Client ID is required")
	private Long clientId;
}
