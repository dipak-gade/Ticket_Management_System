package com.example.demo.dto;

import com.example.demo.entity.Ticket.Priority;
import com.example.demo.entity.Ticket.Status;

import lombok.Data;

@Data
public class TicketRequestDTO {

	private String title;
	private String description;
	private Priority priority;
	private Status status;
	private String assignedTo;
	private String createdBy;

}
