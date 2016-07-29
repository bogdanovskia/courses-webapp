package com.courses.model;

import java.util.HashSet;
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
@Table(name = "students")
public class Student extends User {

	/*
	 * @ManyToMany(cascade = CascadeType.ALL, mappedBy = "students", fetch =
	 * FetchType.EAGER)
	 * 
	 * @JsonBackReference
	 * 
	 * @Column(nullable = true) private Set<Course> courses;
	 */
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@ElementCollection(targetClass = JoinedStudentCourse.class)
	@JsonManagedReference
	@Column(nullable = true)
	private Set<JoinedStudentCourse> joined = new HashSet<JoinedStudentCourse>();

	@Override
	public boolean isStudent() {
		return true;
	}

	/*
	 * public Set<Course> getCourses() { return courses; }
	 * 
	 * public void setCourses(Set<Course> courses) { this.courses = courses; }
	 */
	@Override
	public String toString() {
		return getUsername() + " " + getFirstName() + " " + getSecondName();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public Set<JoinedStudentCourse> getJoined() {
		return joined;
	}

	public void setJoined(Set<JoinedStudentCourse> joined) {
		this.joined = joined;
	}

}
