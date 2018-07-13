package com.assignment.eight.service;

import java.util.List;

import com.assignment.eight.dto.Book;
import com.assignment.eight.dto.Subject;
import com.assignment.eight.exception.SubjectException;

public interface ISubjectService {
	Long add(Subject sub) throws SubjectException;
	boolean delete(Long subjectId) throws SubjectException;
	Subject get(Long subjectId) throws SubjectException;
	List<Subject> getAll() throws SubjectException;
	Long addBook(Subject sub, Book b) throws SubjectException;
	
}
