package com.courses.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.courses.model.User;
import com.courses.service.UserService;
import com.courses.viewBean.LoginBean;

@RestController
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("loginBean", new LoginBean());
		return modelAndView;
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcome(){
		return new ModelAndView("welcome");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView executeLogin(@ModelAttribute("loginBean") LoginBean loginBean, HttpSession session) {
		ModelAndView modelAndView = null;
		boolean isValidUser = userService.isValidUser(loginBean.getUsername(), loginBean.getPassword());
		
		if(! isValidUser) {
			modelAndView = new ModelAndView("login");
			modelAndView.addObject("loginBean", new LoginBean());
			modelAndView.addObject("message", "Invalid credentials!");
			return modelAndView;
		}
		
		User user = userService.getUserByUsername(loginBean.getUsername());
		
		session.setAttribute("loggedUser", user);
		modelAndView = new ModelAndView("welcome");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView executeLogout(HttpSession session){
		session.removeAttribute("loggedUser");
		return new ModelAndView("index");
	}
	
}
