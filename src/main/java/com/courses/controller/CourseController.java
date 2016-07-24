package com.courses.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.courses.model.Course;
import com.courses.model.Lesson;
import com.courses.model.Professor;
import com.courses.model.Student;
import com.courses.model.User;
import com.courses.service.CourseService;
import com.courses.service.LessonService;
import com.courses.service.UserService;

@RestController
public class CourseController<T extends User> {

	@Autowired
	CourseService courseService;

	@Autowired
	UserService<T> userService;

	@Autowired
	LessonService lessonService;

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

	@RequestMapping(value = "/ViewCoursesByUser/{id}")
	public ModelAndView viewCourse(@PathVariable("id") long id) {
		Course course = courseService.getById(id);
		System.out.println(course.getLessons());
		ModelAndView modelAndView = new ModelAndView("view_course");
		modelAndView.addObject("course", course);
		return modelAndView;
	}

	@RequestMapping(value = "/ViewCoursesByUser/{id}/CreateLesson")
	public ModelAndView createLesson(@PathVariable("id") long id, HttpSession session, HttpServletResponse response)
			throws IOException {
		User u = (User) session.getAttribute("loggedUser");
		if (u.isStudent()) {
			response.sendRedirect("../" + id);
		}

		ModelAndView modelAndView = new ModelAndView("create_lesson");
		Course c = courseService.getById(id);
		modelAndView.addObject("course", c);
		modelAndView.addObject("lesson", new Lesson());
		return modelAndView;
	}

	@RequestMapping(value = "/ViewCoursesByUser/{id}/newlesson")
	public void appendLessonToCourse(@PathVariable("id") long id, @ModelAttribute(value = "lesson") Lesson lesson,
			HttpServletResponse response) throws IOException {
		if (lesson.getTitle().equals("")) {
			response.sendRedirect("../" + id);
			return;
		}

		Course course = courseService.getById(id);
		lesson.setCourse(course);
		course.getLessons().add(lesson);
		lessonService.save(lesson);
		response.sendRedirect("../" + id);
	}

}
