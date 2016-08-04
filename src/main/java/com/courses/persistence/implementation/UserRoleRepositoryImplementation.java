package com.courses.persistence.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.courses.model.UserRole;
import com.courses.persistence.BaseRepository;
import com.courses.persistence.UserRoleRepository;

@Repository
public class UserRoleRepositoryImplementation implements UserRoleRepository {

	@Autowired
	BaseRepository baseRepository;

	public UserRole findById(long id) {
		return baseRepository.getById(UserRole.class, id);
	}

	public List<UserRole> findAll() {
		return baseRepository.find(UserRole.class, null);
	}

	public UserRole save(UserRole u) {
		return baseRepository.saveOrUpdate(u);
	}

	public int deleteById(long id) {
		return baseRepository.delete(UserRole.class, id);
	}

}
