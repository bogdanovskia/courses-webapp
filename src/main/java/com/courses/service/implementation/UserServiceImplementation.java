package com.courses.service.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.courses.model.Course;
import com.courses.model.JoinedStudentCourse;
import com.courses.model.JoinedStudentLesson;
import com.courses.model.Professor;
import com.courses.model.Student;
import com.courses.model.User;
import com.courses.persistence.ProfessorRepository;
import com.courses.persistence.StudentRepository;
import com.courses.persistence.UserRepository;
import com.courses.service.CourseService;
import com.courses.service.JoinedStudentCourseService;
import com.courses.service.JoinedStudentLessonService;
import com.courses.service.UserService;

@Service
public class UserServiceImplementation<T extends User> implements UserService<T> {

	@Autowired
	UserRepository<T> userRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	JoinedStudentLessonService joinedStudentLessonService;

	@Autowired
	JoinedStudentCourseService joinedStudentCourseService;

	@Autowired
	CourseService courseService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public int delete(User user) {
		if (user.isStudent()) {
			Student s = (Student) user;
			for (JoinedStudentLesson j : joinedStudentLessonService.getAll()) {
				if (j.getStudent().equals(s)) {
					joinedStudentLessonService.delete(j);
				}
			}
			for (JoinedStudentCourse j : s.getJoined()) {
				joinedStudentCourseService.delete(j);
			}
			return studentRepository.delete(s);
		} else {
			Professor p = (Professor) user;
			for (Course c : p.getCourses()) {
				courseService.delete(c);
			}
			return professorRepository.delete(p);
		}

	}

	public User getById(long id) {
		return userRepository.getById(id);
	}

	public List<User> getAll() {
		return userRepository.getAll();
	}

	public boolean isValidUser(String username, String password) {
		return userRepository.isValidUser(username, password);
	}

	public User getUserByUsername(String username) {
		return userRepository.getUserByUsername(username);
	}

	public Set<Course> getCourses(User u) {
		return userRepository.getCourses(u);
	}

	@SuppressWarnings("unchecked")
	public T save(T s) {
		s.setPassword(bCryptPasswordEncoder.encode(s.getPassword()));
		if (s.isStudent()) {
			return (T) studentRepository.save((Student) s);
		}
		return (T) professorRepository.save((Professor) s);
	}

	public T getById(long id, String string) {
		if (string.equals("Student")) {
			return (T) studentRepository.getById(id);
		} else
			return (T) professorRepository.getById(id);
	}

	public void deleteCourseFromProfessor(Professor p, Course course) {
		p.getCourses().remove(course);
		professorRepository.save(p);
	}

}
