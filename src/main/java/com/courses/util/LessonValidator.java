package com.courses.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.courses.model.Lesson;
import com.courses.service.LessonService;

@Component
public class LessonValidator implements Validator {

	@Autowired
	LessonService lessonService;

	public boolean supports(Class<?> clazz) {
		return Lesson.class.isAssignableFrom(clazz);
	}

	public void validate(Object arg0, Errors errors) {
		Lesson lesson = (Lesson) arg0;
		if (lesson.getTitle().equals("")) {
			errors.rejectValue("title", "NotEmpty.lesson.title");
		}
		if (lessonService.getByName(lesson.getTitle()) != null) {
			errors.rejectValue("title", "lesson.title.duplicate");
		}
	}

}
