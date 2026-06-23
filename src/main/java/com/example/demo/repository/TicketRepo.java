package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Ticket;
import com.example.demo.entity.Ticket.Priority;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer> {

	List<Ticket> findByUserId(int uId);

	List<Ticket> findByPriority(Priority priority);
}
