package com.courses.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "lessons")
public class Lesson extends BaseEntity implements Comparable<Lesson> {

	@NotNull(message = "Please type the order of this lesson.")
	private int lessonOrder;

	@NotEmpty(message = "Please type the name of the lesson.")
	@Column(unique = true)
	private String title;

	@ManyToOne(targetEntity = Course.class)
	@JoinColumn(name = "course_id")
	@JsonBackReference
	@NotNull
	private Course course;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<LessonDocument> lessonDocuments = new ArrayList<LessonDocument>();

	public int getLessonOrder() {
		return lessonOrder;
	}

	public void setLessonOrder(int lessonOrder) {
		this.lessonOrder = lessonOrder;
	}

	public Lesson() {
		lessonOrder = 0;
		title = "";
		course = null;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public List<LessonDocument> getLessonDocuments() {
		return lessonDocuments;
	}

	public void setLessonDocuments(List<LessonDocument> lessonDocuments) {
		this.lessonDocuments = lessonDocuments;
	}

	public int compareTo(Lesson o) {
		if (Integer.compare(lessonOrder, o.getLessonOrder()) == 0) {
			return title.compareTo(o.getTitle());
		}
		return Integer.compare(lessonOrder, o.getLessonOrder());
	}

	public List<LessonDocument> getVideos() {
		List<LessonDocument> videos = new ArrayList<LessonDocument>();
		for (LessonDocument l : lessonDocuments) {
			if (l.getType().equals("video/mp4")) {
				videos.add(l);
			}
		}
		return videos;
	}
}
