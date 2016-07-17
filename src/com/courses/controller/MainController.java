package com.courses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
		public Student checkStudent(@ModelAttribute("student") Student student){
			baseRepository.saveOrUpdate(student);
			return student;
		}
}
