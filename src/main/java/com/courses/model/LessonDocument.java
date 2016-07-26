package com.courses.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "documents")
public class LessonDocument extends BaseEntity {
	@Column(nullable = false)
	private String name;

	@Column(length = 255)
	private String description;

	@Column(length = 100, nullable = false)
	private String type;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(nullable = false)
	private byte[] content;

	@ManyToOne(optional = false)
	@JoinColumn(name = "lesson_id")
	private Lesson lesson;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + ((name == null) ? 0 : name.hashCode()); return
	 * result; }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return
	 * true; if (obj == null) return false; if (getClass() != obj.getClass())
	 * return false; LessonDocument other = (LessonDocument) obj; if (name ==
	 * null) { if (other.name != null) return false; } else if
	 * (!name.equals(other.name)) return false; return true; }
	 */
}
