package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TicketRequestDTO;
import com.example.demo.dto.TicketResponseDTO;
import com.example.demo.entity.Ticket;
import com.example.demo.entity.Ticket.Priority;
import com.example.demo.entity.Ticket.Status;
import com.example.demo.entity.User;
import com.example.demo.exception.TicketServiceException;
import com.example.demo.repository.TicketRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepo ticketRepo;

	@Autowired
	UserRepo userRepo;

	@Override
	public String addTicket(TicketRequestDTO dto) {

		if (dto.getTitle() == null || dto.getTitle().isBlank()) {
			throw new TicketServiceException("Ticket title is required", HttpStatus.BAD_REQUEST);
		}

		Ticket ticket = new Ticket();

		ticket.setAssignedTo(dto.getAssignedTo());
		ticket.setCreatedAt(LocalDate.now());
		ticket.setCreatedBy(dto.getCreatedBy());
		ticket.setDescription(dto.getDescription());
		ticket.setPriority(dto.getPriority());
		ticket.setStatus(dto.getStatus());
		ticket.setTitle(dto.getTitle());
		ticket.setUpdatedAt(LocalDate.now());

		try {
			ticketRepo.save(ticket);
		} catch (Exception e) {
			throw new TicketServiceException("Save Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return "Ticket Created Successfully";
	}

	@Override
	public List<TicketResponseDTO> getAllTickets() {

		List<Ticket> tickets = ticketRepo.findAll();

		if (tickets.isEmpty()) {
			throw new TicketServiceException("No tickets found", HttpStatus.NOT_FOUND);
		}

		List<TicketResponseDTO> dtos = new ArrayList<>();

		for (Ticket ticket : tickets) {

			TicketResponseDTO dto = new TicketResponseDTO();

			dto.setId(ticket.getId());
			dto.setTitle(ticket.getTitle());
			dto.setDescription(ticket.getDescription());
			dto.setPriority(ticket.getPriority());
			dto.setStatus(ticket.getStatus());
			dto.setCreatedAt(ticket.getCreatedAt());
			dto.setUpdatedAt(ticket.getUpdatedAt());

			dtos.add(dto);
		}
		return dtos;

	}

	@Override
	public String assignTicket(int tId, int uId) {

		Optional<User> optional = userRepo.findById(uId);

		if (optional.isEmpty() || uId <= 0) {
			throw new TicketServiceException("User not found with id : " + uId, HttpStatus.NOT_FOUND);
		}

		User user = optional.get();

		Optional<Ticket> optional2 = ticketRepo.findById(tId);

		if (optional2.isEmpty() || tId <= 0) {
			throw new TicketServiceException("Ticket not found with id : " + tId, HttpStatus.NOT_FOUND);
		}
		Ticket ticket = optional2.get();

		ticket.setUser(user);

		ticketRepo.save(ticket);

		return "Ticket No : " + tId + " is assigned to User " + uId;
	}

	@Override
	public String changePriority(Priority priority, int tId) {

		Optional<Ticket> optional = ticketRepo.findById(tId);

		if (optional.isEmpty() || tId <= 0) {
			throw new TicketServiceException("Ticket is not found", HttpStatus.NOT_FOUND);
		}

		Ticket ticket = optional.get();

		ticket.setPriority(priority);
		try {

			ticketRepo.save(ticket);
		} catch (Exception e) {
			throw new TicketServiceException("Save Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return "Priority Updated Successfully";
	}

	@Override
	public String changeStatus(Status status, int tId) {

		Optional<Ticket> optional = ticketRepo.findById(tId);

		if (optional.isEmpty() || tId <= 0) {
			throw new TicketServiceException("Ticket not found", HttpStatus.NOT_FOUND);
		}

		Ticket ticket = optional.get();

		ticket.setStatus(status);
		try {
			ticketRepo.save(ticket);
		} catch (Exception e) {
			throw new TicketServiceException("save failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return "Status Updated Successfully";
	}

	@Override
	public List<TicketResponseDTO> getTicketsByUser(int uId) {

		if (uId <= 0) {
			throw new TicketServiceException("Invalid user id", HttpStatus.BAD_REQUEST);
		}

		List<Ticket> tickets = ticketRepo.findByUserId(uId);
		if (tickets.isEmpty()) {
			throw new TicketServiceException("No tickets found for user id : " + uId, HttpStatus.NOT_FOUND);
		}

		List<TicketResponseDTO> dtos = new ArrayList<>();

		for (Ticket ticket : tickets) {

			TicketResponseDTO dto = new TicketResponseDTO();

			dto.setId(ticket.getId());
			dto.setTitle(ticket.getTitle());
			dto.setPriority(ticket.getPriority());
			dto.setCreatedAt(ticket.getCreatedAt());
			dto.setDescription(ticket.getDescription());
			dto.setStatus(ticket.getStatus());
			dto.setUpdatedAt(ticket.getUpdatedAt());

			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<TicketResponseDTO> getTicketsByPriority(Priority priority) {

		if (priority == null) {
			throw new TicketServiceException("give valid priority", HttpStatus.BAD_REQUEST);
		}
		List<TicketResponseDTO> dtos = new ArrayList<>();

		List<Ticket> tickets = ticketRepo.findByPriority(priority);
		for (Ticket ticket : tickets) {

			TicketResponseDTO dto = new TicketResponseDTO();

			dto.setId(ticket.getId());
			dto.setTitle(ticket.getTitle());
			dto.setDescription(ticket.getDescription());
			dto.setPriority(ticket.getPriority());
			dto.setStatus(ticket.getStatus());
			dto.setCreatedAt(ticket.getCreatedAt());
			dto.setUpdatedAt(ticket.getUpdatedAt());

			dtos.add(dto);
		}

		return dtos;
	}

}
