package com.courses.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "professors")
public class Professor extends User{

	@Override
	public boolean isStudent() {
		return false;
	}

}
