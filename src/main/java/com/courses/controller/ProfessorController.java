package com.courses.controller;

import javax.servlet.http.HttpSession;

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

import com.courses.model.Course;
import com.courses.model.Professor;
import com.courses.model.User;
import com.courses.service.CourseService;
import com.courses.service.UserService;
import com.courses.util.CourseValidator;
import com.courses.util.UserValidator;

@Controller
public class ProfessorController<T extends User> {

	@Autowired
	UserService<T> userService;

	@Autowired
	CourseService courseService;

	@Autowired
	UserValidator<T> userValidator;

	@Autowired
	CourseValidator courseValidator;

	@InitBinder("professor")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@InitBinder("course")
	protected void initCourseBinder(WebDataBinder binder) {
		binder.setValidator(courseValidator);
	}

	@RequestMapping(value = "/newprofessor", method = RequestMethod.GET)
	public ModelAndView createProfessor() {
		return new ModelAndView("create_professor", "user", new Professor());
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/newprofessor", method = RequestMethod.POST)
	public String checkProfessor(@ModelAttribute("user") @Validated Professor professor, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			return "create_professor";
		}

		if (userService.getUserByUsername(professor.getUsername()) != null) {
			result.rejectValue("username", "user.username.duplicate");
			return "create_professor";

		}

		session.setAttribute("loggedUser", professor);
		System.out.println(professor);
		userService.save((T) professor);
		return "welcome";
	}

	@RequestMapping(value = "/create-course")
	public ModelAndView createCourse() {
		ModelAndView modelAndView = new ModelAndView("create_course");
		modelAndView.addObject("course", new Course());
		return modelAndView;
	}

	@RequestMapping(value = "/create-course", method = RequestMethod.POST)
	public String checkCourse(@ModelAttribute("course") @Validated Course course, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			return "create_course";
		}

		User u = (User) session.getAttribute("loggedUser");
		Professor p = (Professor) u;
		course.setProfessor(p);

		courseService.save(course);

		return "redirect:/welcome";
	}

	@RequestMapping(value = "/user/update/professor")
	public ModelAndView updateProfessor(HttpSession session) {
		Professor s = (Professor) session.getAttribute("loggedUser");
		return new ModelAndView("update_student", "user", s);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/user/update/professor", method = RequestMethod.POST)
	public String updatedUser(@ModelAttribute("user") @Validated Professor user, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			return "update_professor";
		}

		Professor formerProf = (Professor) session.getAttribute("loggedUser");

		Professor p = (Professor) userService.getById(formerProf.getId(), "Professor");
		user.id = p.getId();
		userService.save((T) user);
		session.setAttribute("loggedUser", user);
		return "redirect:/user";
	}
}
