package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TicketRequestDTO;
import com.example.demo.dto.TicketResponseDTO;
import com.example.demo.entity.Ticket.Priority;
import com.example.demo.entity.Ticket.Status;
import com.example.demo.service.TicketService;

@RestController
public class TicketController {

	@Autowired
	TicketService ticketService;

	@PostMapping("/tickets")
	public ResponseEntity<String> addTicket(@RequestBody TicketRequestDTO dto) {
		ticketService.addTicket(dto);
		return new ResponseEntity<String>("Ticket added", HttpStatus.CREATED);
	}

	@GetMapping("/tickets")
	public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {
		return new ResponseEntity<>(ticketService.getAllTickets(), HttpStatus.OK);
	}

	@PutMapping("/tickets/{tId}/assign/{uId}")
	public ResponseEntity<String> assignTicket(@PathVariable int tId, @PathVariable int uId) {
		String response = ticketService.assignTicket(tId, uId);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PatchMapping("/tickets/{tId}/priority/{priority}")
	public ResponseEntity<String> changPriority(@PathVariable Priority priority, @PathVariable int tId) {
		String response = ticketService.changePriority(priority, tId);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@PatchMapping("/tickets/{tId}/status/{status}")
	public ResponseEntity<String> changeStatus(@PathVariable Status status, @PathVariable int tId) {
		String response = ticketService.changeStatus(status, tId);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping("/users/{uId}/tickets")
	public ResponseEntity<List<TicketResponseDTO>> getTicketsByUser(@PathVariable int uId) {
		return new ResponseEntity<List<TicketResponseDTO>>(ticketService.getTicketsByUser(uId), HttpStatus.OK);
	}

	@GetMapping("/tickets/priority/{priority}")
	public ResponseEntity<List<TicketResponseDTO>> getTicketsByPriority(@PathVariable Priority priority) {
		return new ResponseEntity<>(ticketService.getTicketsByPriority(priority), HttpStatus.OK);
	}
	

}
