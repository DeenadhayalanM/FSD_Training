package com.assignment.nine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.assignment.nine.dao.ISubjectDAO;
import com.assignment.nine.dao.SubjectDAOJDBCImpl;
import com.assignment.nine.dto.Subject;
import com.assignment.nine.exception.SubjectException;


public class SubjectServiceImpl implements ISubjectService {
	
	private ISubjectDAO subDAO;
	
	public SubjectServiceImpl() throws SubjectException{
		subDAO=new SubjectDAOJDBCImpl();		
	}
	
	public boolean isValidTitle(String title){
		/*
		 * First Letter should be capital
		 * Minimum length is 4 chars
		 * Maximum length is 20 chars.		
		 */
		Pattern titlePattern = Pattern.compile("[A-Z]\\w{3,19}");
		Matcher titleMatcher = titlePattern.matcher(title);
		
		return titleMatcher.matches();
	}
	
	public boolean isValidSubject(Subject sub) throws SubjectException {
		boolean isValid=false;
		
		List<String> errMsgs = new ArrayList<String>();
		
		if(!isValidTitle(sub.getSubtitle())) {
			errMsgs.add("Title must start with capital and must be between 4 to 20 chars in length");
		}
		
		if(errMsgs.size()==0)
			isValid=true;
		else
			throw new SubjectException(errMsgs.toString());
		
		return isValid;
	}
	

	@Override
	public Long add(Subject sub) throws SubjectException{
		Long SubjectId=null;
		if(sub!=null && isValidSubject(sub)) {
			SubjectId=subDAO.add(sub);
		}
		return SubjectId;
	}

	@Override
	public boolean delete(Long subjectId) throws SubjectException{
		boolean isDone=false;
		if(subjectId!=null){
			subDAO.delete(subjectId);
		}else{
			throw new SubjectException("Subject id is null. Please enter valid id");
		}
		return isDone;
	}

	@Override
	public Subject get(Long subjectId) throws SubjectException{
		return subDAO.get(subjectId);
	}
	
	public ISubjectDAO getDAO() {
		return subDAO;
	}

	@Override
	public List<Subject> getAll() throws SubjectException {
		return subDAO.getAll();
	}

}
