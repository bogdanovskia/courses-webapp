package com.courses.persistence.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.courses.model.User;
import com.courses.persistence.BaseRepository;
import com.courses.persistence.UserRepository;

@Repository
public class UserRepositoryImplementation<T extends User> implements UserRepository<T> {

	@Autowired
	BaseRepository baseRepository;

	public User save(User s) {
		return baseRepository.saveOrUpdate(s);
	}

	public int delete(User s) {
		return baseRepository.delete(User.class, s.getId());
	}

	public User getById(long id) {
		return baseRepository.getById(User.class, id);
	}

	public List<User> getAll() {
		return baseRepository.find(User.class, null);
	}

	public boolean isValidUser(String username, String password) {
		return baseRepository.isValidUser(username, password);
	}

	public T getUserByUsername(String username) {
		return baseRepository.getUserByUsername(username);
	}

}
