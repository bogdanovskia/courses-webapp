package com.courses.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.courses.persistence.BaseRepository;

public class TestStudentCourse {
	
	static BaseRepository baseRepository = new BaseRepository();
	
	public static void main(String[] args) {
		
		
		
		Set<Student> studentsParallelProgramming = new HashSet<Student>();
		Set<Student> studentsObjectOriented = new HashSet<Student>();
		
		Set<Course> parallelCourses = new HashSet<Course>();
		Set<Course> introCourses = new HashSet<Course>();
		
		Student s1 = new Student("idze", "idze", "idze", "idze", "131004");
		Student s2 = new Student("gado", "gado", "gado", "gado", "132021");
		Student s3 = new Student("joska", "joska", "joska", "joska", "131018");
		
		studentsParallelProgramming.add(s1);
		studentsParallelProgramming.add(s2);
		
		studentsObjectOriented.add(s3);
		studentsObjectOriented.add(s1);
		
		Course c1 = new Course("Parallel programming");
		Course c2 = new Course("Object-oriented programming");
		
		parallelCourses.add(c1);
		introCourses.add(c2);
		
		c1.setStudents(studentsParallelProgramming);
		c2.setStudents(studentsObjectOriented);
		
		HashSet<Course> first = new HashSet<Course>();
		first.addAll(parallelCourses);
		first.addAll(introCourses);
		s1.setCourses(first);
		s2.setCourses(first);
		s3.setCourses(introCourses);
		
		baseRepository.saveOrUpdate(s1);
		baseRepository.saveOrUpdate(s2);
		baseRepository.saveOrUpdate(s3);
		
	}
}
