package com.courses.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
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
	@Size(min = 4, message = "Username must must be more than 4 characters")
	@Column(unique = true)
	protected String username;

	@NotEmpty(message = "Password field cannot be empty!")
	@Size(min = 2, message = "Password must be more than 4 characters")
	@Column(columnDefinition = "LONGBLOB")
	protected String password;

	@NotEmpty(message = "First name cannot be empty")
	@Size(min = 4, max = 20, message = "First name must be between 4 and 20 letters long")
	protected String firstName;

	@NotEmpty(message = "Second name cannot be empty")
	@Size(min = 4, max = 20, message = "Second name must be between 4 and 20 letters long")
	protected String secondName;

	@NotEmpty(message = "Email cannot be empty")
	@Email()
	protected String email;

	@NotNull(message = "Gender cannot be empty")
	protected Gender gender;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@NotNull(message = "Birthday cannot be empty")
	@Past(message = "Date must be in the past")

	protected Date birthday;

	@NotNull
	private int enabled = 1;

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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
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
