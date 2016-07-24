package com.courses.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.courses.model.Professor;
import com.courses.model.User;
import com.courses.service.UserService;

@Controller
public class ProfessorController<T extends User> {
	
	@Autowired
	UserService<T> userService;


	@RequestMapping(value = "/newprofessor", method = RequestMethod.GET)
	public ModelAndView createProfessor(){
		return new ModelAndView("create_professor", "professor", new Professor());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/newprofessor", method = RequestMethod.POST)
	public String checkProfessor(@ModelAttribute("professor") @Validated Professor professor, BindingResult result,
			HttpSession session) {
		
		if (result.hasErrors()) {
			return "create_professor";
		}
		
		session.setAttribute("loggedUser", professor);
		System.out.println(professor);
		userService.save((T) professor);
		return "welcome";
	}
}
