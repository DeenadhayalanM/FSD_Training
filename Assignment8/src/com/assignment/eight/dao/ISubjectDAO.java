package com.assignment.eight.dao;


import java.util.List;

import com.assignment.eight.dto.Subject;
import com.assignment.eight.exception.SubjectException;

public interface ISubjectDAO {
	Long add(Subject sub) throws SubjectException;
	boolean delete(Long subjectId) throws SubjectException;
	Subject get(Long subjectId) throws SubjectException;
	List<Subject> getAll() throws SubjectException;

}
