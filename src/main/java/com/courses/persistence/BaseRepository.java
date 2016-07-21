package com.courses.persistence;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.courses.model.BaseEntity;
import com.courses.model.Course;
import com.courses.model.Professor;
import com.courses.model.Student;
import com.courses.model.User;
import com.courses.persistence.helper.PredicateBuilder;

@Repository
@EnableTransactionManagement
public class BaseRepository {

	@PersistenceContext
	private EntityManager em;

	/**
	 * SELECT t.* FROM @Table({type}) as t WHERE t.id={id}
	 *
	 * @param type
	 * @param id
	 * @param <T>
	 * @return
	 */

	public <T extends BaseEntity> T getById(Class<T> type, Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(type);
		final Root<T> root = cq.from(type);

		Predicate byId = cb.equal(root.get("id"), id);

		cq.where(byId);

		TypedQuery<T> query = em.createQuery(cq);

		return query.getSingleResult();
	}

	public <T> List<T> find(Class<T> type, PredicateBuilder<T> predicateBuilder) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(type);
		final Root<T> root = cq.from(type);

		// Predicate securityPredicate = getSecurityPredicate(type);

		if (predicateBuilder != null)
			cq.where(predicateBuilder.toPredicate(cb, cq, root));
		// else
		// cq.where(securityPredicate);
		TypedQuery<T> query = em.createQuery(cq);

		return query.getResultList();
	}

	private <T> Predicate getSecurityPredicate(Class<T> type) {
		return null;
	}

	@Transactional
	public <T extends BaseEntity> T saveOrUpdate(T entity) {
		if (entity.id != null && !em.contains(entity)) {
			entity = em.merge(entity);
		} else {
			em.persist(entity);
		}
		em.flush();
		return entity;

	}

	@Transactional
	public <T> int delete(Class<T> type, Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<T> cd = cb.createCriteriaDelete(type);
		final Root<T> root = cd.from(type);

		CriteriaQuery<T> cq = cb.createQuery(type);
		Predicate byId = cb.equal(root.get("id"), id);
		cd.where(byId);
		int changes = em.createQuery(cd).executeUpdate();
		em.flush();
		return changes;
	}

	public Course getCourseByName(String courseName) {
		Query query = em.createQuery("select c from Course as c where c.courseName = :courseName");
		query.setParameter("courseName", courseName);
		List<Course> courses = query.getResultList();
		if (!courses.isEmpty()) {
			return courses.get(0);
		}
		return null;
	}

	public boolean isValidUser(String username, String password) {
		Query query = em
				.createQuery("select s from Student as s where s.username = :username and s.password = :password");

		query.setParameter("username", username);
		query.setParameter("password", password);

		List<Student> students = query.getResultList();
		if (!students.isEmpty()) {
			return true;
		}

		query = em.createQuery("select p from Professor as p where p.username = :username and p.password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);

		List<Professor> professors = query.getResultList();
		if (!professors.isEmpty()) {
			return true;
		}
		return false;

	}

	public <T extends User> T getUserByUsername(String username) {
		Query query = em.createQuery("select s from Student as s where s.username = :username");
		query.setParameter("username", username);
		List<User> users = query.getResultList();
		if (!users.isEmpty())
			return (T) users.get(0);

		query = em.createQuery("select p from Professor as p where p.username = :username");
		query.setParameter("username", username);
		List<Professor> admins = query.getResultList();
		if (!admins.isEmpty())
			return (T) admins.get(0);

		return null;

	}

	public Set<Course> getCoursesByStudent(long id) {
		Student student = getById(Student.class, id);
		return student.getCourses();
	}

	public Set<Course> getCoursesByProfessor(Long id) {
		Professor p = getById(Professor.class, id);
		return p.getCourses();
	}
}