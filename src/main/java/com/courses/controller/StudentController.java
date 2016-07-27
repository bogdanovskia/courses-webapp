package com.courses.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.courses.model.Course;
import com.courses.model.Student;
import com.courses.model.User;
import com.courses.service.CourseService;
import com.courses.service.UserService;
import com.courses.util.UserValidator;

@Controller
public class StudentController<T extends User> {

	@Autowired
	UserService<T> userService;

	@Autowired
	CourseService courseService;

	@Autowired
	UserValidator<T> userValidator;

	@InitBinder("student")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

	@RequestMapping(value = "/newstudent", method = RequestMethod.GET)
	public ModelAndView createStudent() {
		Student student = new Student();
		return new ModelAndView("create_student", "user", student);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/newstudent", method = RequestMethod.POST)
	public String checkStudent(@ModelAttribute("user") @Valid Student student, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			return "create_student";
		}

		if (userService.getUserByUsername(student.getUsername()) != null) {
			result.rejectValue("username", "user.username.duplicate");
			return "create_student";

		}

		session.setAttribute("loggedUser", student);
		System.out.println(student);
		userService.save((T) student);
		return "welcome";
	}

	@RequestMapping(value = "/view-courses/enroll-to-course")
	public String checkCourse(@RequestParam(value = "course") String courseName, HttpSession session) throws Exception {
		Student student = (Student) session.getAttribute("loggedUser");
		Course course = courseService.getByName(courseName);

		if (course != null) {
			Set<Student> students = course.getStudents();

			// checking if the student is already enrolled in the course
			if (students.contains(student)) {
				System.out.println("Already enrolled in this course...");
				return "redirect:/welcome";
			}
			students.add(student);
			course.setStudents(students);

			Set<Course> courses = student.getCourses();
			courses.add(course);
			student.setCourses(courses);

			courseService.save(course);

			return "redirect:/welcome";

			// studentService.save(student);
		} else {
			throw new Exception("Failed to save new student!");
		}
	}
}
