package com.courses.model;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "students")
public class Student extends User{
	
	private String indeks;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "students")
	@JsonBackReference 
	private Set<Course> courses;
	
	public Student(){
		super();
		courses = new HashSet<Course>();
	}

	public Student(String username, String password, String firstName, String secondName) {
		super(username, password, firstName, secondName);
		courses = new HashSet<Course>();
	}
	public Student(String username, String password, String firstName, String secondName,String index) {
		super(username, password, firstName, secondName);
		this.indeks = index;
		courses = new HashSet<Course>();
	}

	public String getIndex() {
		return indeks;
	}



	public void setIndex(String index) {
		this.indeks = index;
	}

	@Override
	public boolean isStudent() {
		return true;
	}
	
	public Set<Course> getCourses(){
		return courses;
	}



	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	@Override
	public String toString() {
		return indeks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((indeks == null) ? 0 : indeks.hashCode());
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
		Student other = (Student) obj;
		if (indeks == null) {
			if (other.indeks != null)
				return false;
		} else if (!indeks.equals(other.indeks))
			return false;
		return true;
	}
	
	
	
	
}
