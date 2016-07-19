package com.courses.controller;

import java.util.HashSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.courses.model.Course;
import com.courses.model.Student;
import com.courses.persistence.BaseRepository;

@RestController
public class MainController {
		
		@Autowired
		BaseRepository baseRepository;
	
		@RequestMapping(value = "/hello")
		public ModelAndView hello(){
			return new ModelAndView("hello");
		}
		
		@RequestMapping(value = "/CreateStudent")
		public ModelAndView createStudent(){
			return new ModelAndView("create_student", "student", new Student());
		}
		
		@RequestMapping(value = "/CheckStudent")
		public ModelAndView checkStudent(@ModelAttribute("student") Student student, HttpSession session){
			session.setAttribute("student", student);
			return new ModelAndView("select_course", "course", new Course());
		}
		@RequestMapping(value = "/CheckCourse")
		public Student checkCourse(@ModelAttribute("course") Course course, HttpSession session){
			Student s = (Student) session.getAttribute("student");
			HashSet<Course> courses = (HashSet<Course>) s.getCourses();
			courses.add(course);
			
			HashSet<Student> students = (HashSet<Student>) course.getStudents();
			students.add(s);
			
			System.out.println(s + " Courses:");
			for(Course c : s.getCourses()){
				System.out.println(c);
			}
			System.out.println(course + " Students:");
			for(Student student : course.getStudents()){
				System.out.println(student);
			}
			
			baseRepository.saveOrUpdate(s);
			return s;
		}
}
