package com.courses.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.courses.model.User;
import com.courses.service.UserService;

@Component
public class UserValidator<T extends User> implements Validator {

	@Autowired
	UserService<T> userService;

	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object arg0, Errors errors) {
		User user = (User) arg0;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.user.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.user.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secondName", "NotEmpty.user.secondName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.user.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.user.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "NotEmpty.user.age");

	}

}
