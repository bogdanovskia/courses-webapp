package com.courses.model;

public class Professor extends User{

	@Override
	public boolean isStudent() {
		return false;
	}

}
