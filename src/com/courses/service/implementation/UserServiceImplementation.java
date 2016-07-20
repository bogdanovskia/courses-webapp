package com.courses.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.courses.model.User;
import com.courses.persistence.UserRepository;
import com.courses.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User save(User s) {
		return userRepository.save(s);
	}

	@Override
	public int delete(User s) {
		return userRepository.delete(s);
	}

	@Override
	public User getById(long id) {
		return userRepository.getById(id);
	}

	@Override
	public List<User> getAll() {
		return userRepository.getAll();
	}

	@Override
	public boolean isValidUser(String username, String password) {
		return userRepository.isValidUser(username, password);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.getUserByUsername(username);
	}

}
