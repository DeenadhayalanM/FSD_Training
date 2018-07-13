package com.assignment.eight.dto;

import java.io.Serializable;
import java.util.Set;

public class Subject implements Serializable{
	
	private long subjectId;
	private String subtitle;
	private int durationInHours;
	private Set<Book> Books;
	
	public Subject() {
		/* Default constructor*/
	}
	
	public Subject(long subjectId, String subtitle, int durationInHours, Set<Book> books) {
		super();
		this.subjectId = subjectId;
		this.subtitle = subtitle;
		this.durationInHours = durationInHours;
		Books = books;
	}
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public int getDurationInHours() {
		return durationInHours;
	}
	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}
	public Set<Book> getBooks() {
		return Books;
	}
	public void setBooks(Set<Book> books) {
		Books = books;
	}
	
}
