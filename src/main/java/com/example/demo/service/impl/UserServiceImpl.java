package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.UserServiceException;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Override
	public String addUser(UserRequestDTO dto) {

		if (dto.getName() == null || dto.getName().isBlank()) {
			throw new UserServiceException(HttpStatus.BAD_REQUEST, "User name is required");
		}

		User user = new User();

		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setRole(dto.getRole());

		try {
			userRepo.save(user);

		} catch (Exception ex) {
			throw new UserServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Save Failed");
		}
		return "User Created successfully";
	}

	@Override
	public List<UserResponseDTO> getAllUsers() {

		List<UserResponseDTO> dtos = new ArrayList<>();

		List<User> users = userRepo.findAll();

		if (users.isEmpty()) {
			throw new UserServiceException(HttpStatus.NOT_FOUND, "Users not found");
		}

		for (User user : users) {

			UserResponseDTO dto = new UserResponseDTO();

			dto.setId(user.getId());
			dto.setEmail(user.getEmail());
			dto.setName(user.getName());
			dto.setRole(user.getRole());

			dtos.add(dto);

		}
		return dtos;
	}

}
