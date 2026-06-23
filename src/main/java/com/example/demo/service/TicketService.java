package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TicketRequestDTO;
import com.example.demo.dto.TicketResponseDTO;
import com.example.demo.entity.Ticket.Priority;
import com.example.demo.entity.Ticket.Status;

public interface TicketService {

	public String addTicket(TicketRequestDTO dto);

	public List<TicketResponseDTO> getAllTickets();

	public String assignTicket(int tId, int uId);

	public String changePriority(Priority priority, int tId);

	public String changeStatus(Status status, int tId);

	public List<TicketResponseDTO> getTicketsByUser(int uId);

	public List<TicketResponseDTO> getTicketsByPriority(Priority priority);

}
