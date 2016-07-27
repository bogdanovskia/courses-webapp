package com.courses.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.courses.model.Course;
import com.courses.model.FileBucket;
import com.courses.model.Lesson;
import com.courses.model.LessonDocument;
import com.courses.model.User;
import com.courses.service.CourseService;
import com.courses.service.LessonDocumentService;
import com.courses.service.LessonService;
import com.courses.service.UserService;
import com.courses.util.FileValidator;
import com.courses.util.LessonValidator;

@Controller
@RequestMapping(value = "/view-courses-user/*")
public class CourseController<T extends User> {

	@Autowired
	CourseService courseService;

	@Autowired
	UserService<T> userService;

	@Autowired
	LessonService lessonService;

	@Autowired
	LessonDocumentService lessonDocumentService;

	@Autowired
	FileValidator fileValidator;

	@Autowired
	LessonValidator lessonValidator;

	@InitBinder("fileBucket")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	@InitBinder("lesson")
	protected void initLessonBiner(WebDataBinder binder) {
		binder.setValidator(lessonValidator);
	}

	@RequestMapping(value = "")
	public ModelAndView viewCoursesByUser(HttpSession session) {
		User u = (User) session.getAttribute("loggedUser");
		ModelAndView modelAndView = new ModelAndView("view_courses");

		Set<Course> courses = userService.getCourses(u);
		modelAndView.addObject("courses", courses);
		return modelAndView;
	}

	@RequestMapping(value = "/{id}")
	public ModelAndView viewCourse(@PathVariable("id") long id) {
		Course course = courseService.getById(id);
		ModelAndView modelAndView = new ModelAndView("view_course");
		modelAndView.addObject("course", course);
		return modelAndView;
	}

	@RequestMapping(value = "/{id}/create-lesson")
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

	@RequestMapping(value = "/{id}/create-lesson", method = RequestMethod.POST)
	public String appendLessonToCourse(@PathVariable("id") long id,
			@ModelAttribute(value = "lesson") @Validated Lesson lesson, BindingResult result,
			HttpServletResponse response) throws IOException {
		if (result.hasErrors()) {
			System.out.println();
			System.out.println("DA");
			System.out.println();
			return "create_lesson";
		}

		Course course = courseService.getById(id);
		lesson.setCourse(course);
		// course.getLessons().add(lesson);
		lessonService.save(lesson);
		return "redirect:/view-courses-user/" + id;
	}

	@RequestMapping(value = "/viewlesson/{l-id}", method = RequestMethod.GET)
	public ModelAndView viewLesson(@PathVariable("l-id") long lid) {
		Lesson lesson = lessonService.getById(lid);

		ModelAndView modelAndView = new ModelAndView("view_lesson");
		modelAndView.addObject("lesson", lesson);
		System.out.println(lesson.getVideos().size());
		modelAndView.addObject("videos", lesson.getVideos());
		modelAndView.addObject("fileBucket", new FileBucket());
		return modelAndView;
	}

	@RequestMapping(value = "/viewlesson/{l-id}", method = RequestMethod.POST)
	public String uploadDocument(@PathVariable("l-id") int lid, @Valid FileBucket fileBucket, BindingResult result,
			Model model) throws IOException {
		Lesson lesson = lessonService.getById(lid);
		if (result.hasErrors()) {
			System.out.println("validation errors");
			model.addAttribute("lesson", lesson);
			return "view_lesson";
		} else {
			if (lessonDocumentService.containsName(fileBucket.getFile().getOriginalFilename(),
					lesson.getLessonDocuments())) {
				result.rejectValue("file", "document-name.duplicate");
				model.addAttribute("lesson", lesson);
				return "view_lesson";
			}
			System.out.println("Fetching file");
			System.out.println();
			model.addAttribute("lesson", lesson);

			saveDocument(fileBucket, lesson);

			return "redirect:/view-courses-user/viewlesson/" + lesson.getId();
		}
	}

	private void saveDocument(FileBucket fileBucket, Lesson lesson) throws IOException {
		LessonDocument lessonDocument = new LessonDocument();

		MultipartFile multipartFile = fileBucket.getFile();

		lessonDocument.setName(multipartFile.getOriginalFilename());
		lessonDocument.setDescription(fileBucket.getDescription());
		lessonDocument.setType(multipartFile.getContentType());
		System.out.println(lessonDocument.getType());
		lessonDocument.setContent(multipartFile.getBytes());
		lessonDocument.setLesson(lesson);

		lessonDocumentService.saveDocument(lessonDocument);
	}

	@RequestMapping(value = "/viewlesson/{l-id}/download-document-{d-id}")
	public String downloadDocument(@PathVariable("d-id") int did, @PathVariable("l-id") int lid,
			HttpServletResponse response) throws IOException {

		LessonDocument lessonDocument = lessonDocumentService.findById(did);
		response.setContentType(lessonDocument.getType());
		response.setContentLength(lessonDocument.getContent().length);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + lessonDocument.getName() + "\"");

		FileCopyUtils.copy(lessonDocument.getContent(), response.getOutputStream());

		return "redirect:/view-courses-user/viewlesson/" + lid;
	}

	@RequestMapping(value = "/viewlesson/{l-id}/delete-document-{d-id}")
	public String deleteDocument(@PathVariable("l-id") int lid, @PathVariable("d-id") int did) {
		lessonDocumentService.deleteById(did);
		return "redirect:/view-courses-user/viewlesson/" + lid;
	}
}
