package com.courses.model;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "students")
public class Student extends User{
	
	private String index;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "students")
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
		this.index = index;
		courses = new HashSet<Course>();
	}





	public String getIndex() {
		return index;
	}



	public void setIndex(String index) {
		this.index = index;
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
	
	
}
