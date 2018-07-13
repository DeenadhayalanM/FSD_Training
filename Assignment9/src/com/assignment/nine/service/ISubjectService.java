package com.assignment.nine.service;

import java.util.List;

import com.assignment.nine.dto.Subject;
import com.assignment.nine.exception.SubjectException;

public interface ISubjectService {
	Long add(Subject sub) throws SubjectException;
	boolean delete(Long subjectId) throws SubjectException;
	Subject get(Long subjectId) throws SubjectException;
	List<Subject> getAll() throws SubjectException;
}
