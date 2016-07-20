package com.courses.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@MappedSuperclass
@Table(name = "users")
public abstract class User extends BaseEntity{
	
	private String username;
	private String password;
	
	private String firstName;
	private String secondName;
	
	public User(){
		
	}
	
	public User(String username, String password, String firstName, String secondName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.secondName = secondName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	
	public abstract boolean isStudent();
	
	
	@Override
	public String toString() {
		return username + " " + firstName + " " + secondName;
	}
}
