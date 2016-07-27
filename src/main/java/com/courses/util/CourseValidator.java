package com.courses.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.courses.model.Course;
import com.courses.service.CourseService;

@Component
public class CourseValidator implements Validator {

	@Autowired
	CourseService courseService;

	public boolean supports(Class<?> clazz) {
		return Course.class.isAssignableFrom(clazz);
	}

	public void validate(Object arg0, Errors errors) {
		Course course = (Course) arg0;

		if (course.getCourseName().equals("")) {
			errors.rejectValue("courseName", "NotEmpty.course.courseName");
		}

		if (courseService.getByName(course.getCourseName()) != null) {
			errors.rejectValue("courseName", "course.courseName.duplicate");
		}
	}

}
