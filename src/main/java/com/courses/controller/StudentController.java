package com.courses.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.courses.model.Course;
import com.courses.model.JoinedStudentCourse;
import com.courses.model.JoinedStudentLesson;
import com.courses.model.Lesson;
import com.courses.model.Student;
import com.courses.model.User;
import com.courses.model.UserRole;
import com.courses.service.CourseService;
import com.courses.service.JoinedStudentCourseService;
import com.courses.service.JoinedStudentLessonService;
import com.courses.service.UserRoleService;
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

	@Autowired
	JoinedStudentCourseService joinedStudentCourseService;

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	JoinedStudentLessonService joinedStudentLessonService;

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
	public String checkStudent(@ModelAttribute("user") @Validated Student student, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			return "create_student";
		}

		if (userService.getUserByUsername(student.getUsername()) != null) {
			result.rejectValue("username", "user.username.duplicate");
			return "create_student";

		}

		System.out.println(student);
		student = (Student) userService.save((T) student);
		session.setAttribute("loggedUser", student);

		UserRole u = new UserRole();
		u.setUsername(student.getUsername());
		u.setRole("ROLE_USER");
		userRoleService.save(u);

		return "login";
	}

	@RequestMapping(value = "/view-courses/enroll-to-course")
	public String checkCourse(@RequestParam(value = "course") String courseName, HttpSession session) throws Exception {
		Student student = (Student) session.getAttribute("loggedUser");
		Course course = courseService.getByName(courseName);

		if (course != null) {
			/*
			 * Set<Student> students = course.getJoined().getStudents();
			 * 
			 * // checking if the student is already enrolled in the course if
			 * (students.contains(student)) {
			 * System.out.println("Already enrolled in this course..."); return
			 * "redirect:/welcome"; } students.add(student);
			 * course.setStudents(students);
			 * 
			 * Set<Course> courses = student.getCourses(); courses.add(course);
			 * student.setCourses(courses);
			 * 
			 * courseService.save(course);
			 * 
			 * return "redirect:/welcome";
			 */

			if (joinedStudentCourseService.getByStudentCourse(student, course) != null) {
				return "redirect:/welcome";
			}

			JoinedStudentCourse joined = new JoinedStudentCourse();
			joined.setCourse(course);
			joined.setStudent(student);

			joinedStudentCourseService.save(joined);

			ArrayList<JoinedStudentLesson> joinedLessons = new ArrayList<JoinedStudentLesson>();
			for (Lesson l : course.getLessons()) {
				if (joinedStudentLessonService.getByIdAndStudent(l.getId(), student.getId()) != null) {
					continue;
				}
				JoinedStudentLesson joinedLesson = new JoinedStudentLesson();
				joinedLesson.setLesson(l);
				joinedLesson.setPassed(false);
				joinedLesson.setStudent(student);
				joinedLessons.add(joinedLesson);
			}

			for (JoinedStudentLesson j : joinedLessons) {
				joinedStudentLessonService.save(j);
			}

			return "redirect:/welcome";

			// studentService.save(student);
		} else {
			throw new Exception("Failed to save new student!");
		}
	}

	@RequestMapping(value = "/user/update/student")
	public ModelAndView updateStudent(HttpSession session) {
		Student s = (Student) session.getAttribute("loggedUser");
		return new ModelAndView("update_student", "user", s);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/user/update/student", method = RequestMethod.POST)
	public String updatedUser(@ModelAttribute("user") @Validated Student user, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			return "update_student";
		}

		Student formerUser = (Student) session.getAttribute("loggedUser");

		Student student = (Student) userService.getById(formerUser.getId(), "Student");
		user.id = student.getId();
		userService.save((T) user);
		session.setAttribute("loggedUser", user);

		return "redirect:/user";
	}

	@RequestMapping(value = "/view-favourites")
	public String viewFavourites(HttpSession session, Model model) {

		@SuppressWarnings("unchecked")
		T user = (T) session.getAttribute("loggedUser");

		if (!user.isStudent()) {
			return "redirect:/welcome";
		}

		List<JoinedStudentCourse> joined = joinedStudentCourseService.getByStudent((Student) user);
		List<Course> courses = new ArrayList<Course>();
		for (JoinedStudentCourse j : joined) {
			if (j.isFavourite()) {
				courses.add(j.getCourse());
			}
		}
		model.addAttribute("courses", courses);
		return "view_favourites";

	}
}
