package com.courses.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.courses.model.Student;
import com.courses.model.User;
import com.courses.service.UserService;

@Controller
public class StudentController<T extends User> {

	@Autowired
	UserService<T> userService;


	@RequestMapping(value = "/newstudent", method = RequestMethod.GET)
	public ModelAndView createStudent(){
		Student student = new Student();
		return new ModelAndView("create_student", "student", student);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/newstudent", method = RequestMethod.POST)
	public String checkStudent(@ModelAttribute("student") @Validated Student student, BindingResult result,
			HttpSession session) {
		
		if (result.hasErrors()) {
			return "create_student";
		}
		
		session.setAttribute("loggedUser", student);
		System.out.println(student);
		userService.save((T) student);
		return "welcome";
	}

}
