package com.courses.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.courses.model.Student;
import com.courses.model.User;
import com.courses.service.UserService;

@RestController
public class StudentController<T extends User> {

	@Autowired
	UserService<T> userService;

	@RequestMapping(value = "/CreateStudent")
	public ModelAndView createStudent() {
		return new ModelAndView("create_student", "student", new Student());
	}

	@RequestMapping(value = "/CheckStudent")
	public ModelAndView checkStudent(@ModelAttribute("student") Student student, HttpSession session) {
		session.setAttribute("loggedUser", student);
		System.out.println(student);
		userService.save((T) student);
		return new ModelAndView("welcome");
	}

}
