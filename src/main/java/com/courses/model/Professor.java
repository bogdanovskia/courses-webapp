package com.courses.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "professors")
public class Professor extends User {

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "professor", cascade = CascadeType.ALL)
	@ElementCollection(targetClass = Course.class)
	@JsonManagedReference
	@Column(nullable = true)
	private Set<Course> courses;

	@Override
	public boolean isStudent() {
		return false;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return getFirstName() + " " + getSecondName();
	}

}
