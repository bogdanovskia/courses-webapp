package com.courses.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.courses.model.User;
import com.courses.service.UserService;
import com.courses.viewBean.LoginBean;

@Controller
public class LoginController<T extends User> {

	@Autowired
	UserService<T> userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("loginBean", new LoginBean());
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String executeLogin(@ModelAttribute("loginBean") LoginBean loginBean, HttpSession session, Model model) {
		boolean isValidUser = userService.isValidUser(loginBean.getUsername(), loginBean.getPassword());

		if (!isValidUser) {
			model.addAttribute("loginBean", new LoginBean());
			model.addAttribute("message", "Invalid credentials!");
			return "login";
		}

		User user = userService.getUserByUsername(loginBean.getUsername());

		session.setAttribute("loggedUser", user);
		return "redirect:/welcome";

	}

	@RequestMapping(value = "/logout")
	public String executeLogout(HttpSession session) {
		session.removeAttribute("loggedUser");
		return "redirect:/";
	}

}
