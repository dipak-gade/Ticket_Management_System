package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;

public interface UserService {

	public String addUser(UserRequestDTO dto);

	public List<UserResponseDTO> getAllUsers();
}
