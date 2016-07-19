package com.courses.seed;

import java.io.File;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courses.model.Course;
import com.courses.model.Student;
import com.courses.persistence.CourseRepository;
import com.courses.persistence.StudentRepository;
import com.courses.persistence.implementation.CourseRepositoryImplementation;
import com.courses.persistence.implementation.StudentRepositoryImplementation;

public class Seed {

	public static void main(String[] args) {


		Student s1 = new Student("ignatij", "ignatij", "Игнатиј", "Гичевски");
		Student s2 = new Student("jove", "jove", "Јове", "Костандиновски");
		Student s3 = new Student("ivan", "ivan", "Иван", "Спирковски");
		Student s4 = new Student("marko", "marko", "Марко", "Смилевски");
		Student s5 = new Student("ace", "ace", "Александар", "Богдановски");

		Course c1 = new Course("Algorithms and data structures");
		Course c2 = new Course("Intro to parallel programming");
		Course c3 = new Course("Android programming");
		Course c4 = new Course("Java Spring");
		Course c5 = new Course("Machine learning and data mining techniques");

		HashSet<Course> courses = (HashSet<Course>) s1.getCourses();

		courses.add(c1);
		courses.add(c2);
		courses.add(c5);
		s1.setCourses(courses);

		courses = (HashSet<Course>) s2.getCourses();
		courses.add(c4);
		courses.add(c5);
		s2.setCourses(courses);

		courses = (HashSet<Course>) s3.getCourses();
		courses.add(c1);
		courses.add(c3);
		courses.add(c5);
		s3.setCourses(courses);

		courses = (HashSet<Course>) s4.getCourses();
		courses.add(c5);
		s4.setCourses(courses);

		courses = (HashSet<Course>) s5.getCourses();
		courses.add(c3);
		courses.add(c4);
		s5.setCourses(courses);
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(s1);
	}

}
