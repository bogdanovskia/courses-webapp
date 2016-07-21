package com.courses.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.courses.model.Course;
import com.courses.model.Professor;
import com.courses.model.Student;
import com.courses.model.User;
import com.courses.service.CourseService;
import com.courses.service.StudentService;
import com.courses.service.UserService;

@RestController
public class MainController {

	@Autowired
	StudentService studentService;

	@Autowired
	UserService userService;

	@Autowired
	CourseService courseService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView printWelcome(HttpSession session) {
		if (session.getAttribute("loggedUser") != null) {
			return new ModelAndView("welcome");
		}
		return new ModelAndView("index");

	}

	// @RequestMapping(value = "/CreateStudent")
	// public ModelAndView createStudent() {
	// return new ModelAndView("create_student", "student", new Student());
	// }

	// @RequestMapping(value = "/CheckStudent")
	// public ModelAndView checkStudent(@ModelAttribute("student") Student
	// student, HttpSession session) {
	// session.setAttribute("loggedUser", student);
	// System.out.println(student);
	// studentService.save(student);
	// return new ModelAndView("welcome");
	// }

	@RequestMapping(value = "/ViewCourses")
	public ModelAndView viewCourses() {
		ModelAndView modelAndView = new ModelAndView("select_course");
		modelAndView.addObject("courses", courseService.getAll());
		return modelAndView;
	}

	@RequestMapping(value = "/EnrollToCourse")
	public ModelAndView checkCourse(@RequestParam(value = "course") String courseName, HttpSession session)
			throws Exception {
		Student student = (Student) session.getAttribute("loggedUser");
		Course course = courseService.getByName(courseName);

		ModelAndView modelAndView = null;

		if (course != null) {
			Set<Student> students = course.getStudents();
			students.add(student);
			course.setStudents(students);

			Set<Course> courses = student.getCourses();
			courses.add(course);
			student.setCourses(courses);

			courseService.save(course);

			modelAndView = new ModelAndView("welcome");

			// studentService.save(student);
		} else {
			throw new Exception("Failed to save new student!");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/ViewCoursesByUser")
	public ModelAndView viewCoursesByUser(HttpSession session) {
		User u = (User) session.getAttribute("loggedUser");
		ModelAndView modelAndView = new ModelAndView("view_courses");

		Set<Course> courses = userService.getCourses(u);
		modelAndView.addObject("courses", courses);
		return modelAndView;
	}

	@RequestMapping(value = "/CreateCourse")
	public ModelAndView createCourse() {
		ModelAndView modelAndView = new ModelAndView("create_course");
		modelAndView.addObject("course", new Course());
		return modelAndView;
	}

	@RequestMapping(value = "/CheckCourse")
	public ModelAndView checkCourse(@ModelAttribute("course") Course course, HttpSession session) {
		User u = (User) session.getAttribute("loggedUser");
		Professor p = (Professor) u;
		// Set<Course> courses = p.getCourses();
		course.setProfessor(p);
		// courses.add(course);
		// p.setCourses(courses);

		courseService.save(course);
		ModelAndView modelAndView = new ModelAndView("welcome");
		return modelAndView;
	}
}
