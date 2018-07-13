package com.assignment.nine.dao;

public interface IQueryMapper {
	public static final String ADD_SUBJECT_QRY = 
			"INSERT INTO subjects VALUES(?,?,?)";
	public static final String DEL_SUBJECT_QRY = 
			"DELETE FROM subjects WHERE subjectId=?";
	public static final String GET_ALL_SUBJECT_QRY = 
			"SELECT * FROM subjects";
	public static final String GET_SUBJECT_QRY = 
			"SELECT * FROM subjects WHERE subjectId=?";

}
