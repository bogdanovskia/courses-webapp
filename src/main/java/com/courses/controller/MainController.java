package com.courses.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.courses.model.Professor;
import com.courses.model.Student;
import com.courses.model.User;
import com.courses.service.CourseService;
import com.courses.service.JoinedStudentCourseService;
import com.courses.service.JoinedStudentLessonService;
import com.courses.service.StudentService;
import com.courses.service.UserService;
import com.courses.util.UserValidator;

@Controller
public class MainController<T extends User> {

	public final String Student = "STUDENT";
	public final String Professor = "PROFESSOR";

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	@Autowired
	UserService<T> userService;

	@Autowired
	JoinedStudentCourseService joinedStudentCourseService;

	@Autowired
	JoinedStudentLessonService joinedStudentLessonService;

	@Autowired
	UserValidator<T> userValidator;

	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public ModelAndView viewWelcome(HttpSession session, HttpServletRequest request) {
		User user = userService.getUserByUsername(request.getUserPrincipal().getName());
		if (user == null) {
			return new ModelAndView("login");
		}
		session.setAttribute("loggedUser", user);
		return new ModelAndView("welcome");
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public ModelAndView createUser() {
		return new ModelAndView("user_type");
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public ModelAndView newUser(@RequestParam(value = "type") String type) {
		if (type.equals(Student)) {
			Student s = new Student();
			ModelAndView modelAndView = new ModelAndView("create_student");
			modelAndView.addObject("user", s);
			return modelAndView;
		} else {
			Professor p = new Professor();
			ModelAndView modelAndView = new ModelAndView("create_professor");
			modelAndView.addObject("user", p);
			return modelAndView;
		}
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public ModelAndView addNewUser(@RequestParam(value = "type") String type) {
		if (type.equals(Student)) {
			Student s = new Student();
			ModelAndView modelAndView = new ModelAndView("create_student");
			modelAndView.addObject("user", s);
			return modelAndView;
		} else {
			Professor p = new Professor();
			ModelAndView modelAndView = new ModelAndView("create_professor");
			modelAndView.addObject("user", p);
			return modelAndView;
		}
	}

	@RequestMapping(value = "/view-courses")
	public ModelAndView viewCourses() {
		ModelAndView modelAndView = new ModelAndView("select_course");
		modelAndView.addObject("courses", courseService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView viewUser(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("view_user");
		@SuppressWarnings("unchecked")
		T user = (T) session.getAttribute("loggedUser");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.GET)
	public String updateUser(HttpSession session) {

		@SuppressWarnings("unchecked")
		T user = (T) session.getAttribute("loggedUser");
		if (user.isStudent()) {
			return "redirect:/user/update/student";
		} else {
			return "redirect:/user/update/professor";
		}

	}

	@RequestMapping(value = "/user/delete", method = RequestMethod.GET)
	public String deleteUser(HttpSession session, HttpServletRequest request) throws ServletException {
		@SuppressWarnings("unchecked")
		T user = (T) session.getAttribute("loggedUser");

		userService.delete(user);
		session.removeAttribute("loggedUser");
		request.logout();
		return "redirect:/";
	}
}
