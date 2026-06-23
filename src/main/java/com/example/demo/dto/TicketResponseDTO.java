package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.entity.Ticket.Priority;
import com.example.demo.entity.Ticket.Status;

import lombok.Data;

@Data
public class TicketResponseDTO {

	private int id;
	private String title;
	private String description;
	private Priority priority;
	private Status status;
	private LocalDate createdAt;
	private LocalDate updatedAt;
}
