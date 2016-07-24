package com.courses.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
@Table(name = "users")
public abstract class User extends BaseEntity {

	@NotEmpty(message = "Username cannot be empty!")
	@Size(min = 4, max = 10, message = "Username must between 4 and 10 letters")
	protected String username;

	@NotEmpty(message = "Password field cannot be empty!")
	@Size(min = 2, max = 10, message = "Password must be between 2 and 10 letters long")
	protected String password;

	@NotEmpty(message = "First name cannot be empty")
	@Size(min = 4, max = 20, message = "First name must be between 4 and 20 letters long")
	protected String firstName;

	@NotEmpty(message = "Second name cannot be empty") 
	@Size(min = 4, max = 20, message = "Second name must be between 4 and 20 letters long")
	protected String secondName;

	@NotEmpty(message = "Email cannot be empty")
	@Email
	protected String email; 

	@NotNull(message = "Age cannot be empty")
	@Min(value = 15, message = "Must be older than 15 years")
	@Max(value = 100, message = "Please, stop joking!")
	protected Integer age;

	@NotNull(message = "Gender cannot be empty")
	protected Gender gender;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@NotNull(message = "Birthday cannot be empty")
	@Past(message = "Date must be in the past")
	
	protected Date birthday;

	public enum Gender {
		MALE, FEMALE
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public abstract boolean isStudent();

	@Override
	public String toString() {
		return username + " " + firstName + " " + secondName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
