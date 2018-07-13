package com.assignment.nine.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.assignment.nine.dto.Book;
import com.assignment.nine.dto.Subject;
import com.assignment.nine.exception.SubjectException;

public class SubjectDAOJDBCImpl implements ISubjectDAO {
	
	public static final String DATA_STORE_FILE_NAME = "./bookstore.dat";
	
	private Map<Long, Subject> subjects;
	private Logger logger=Logger.getLogger("dao");
	
	@SuppressWarnings("unchecked")
	public SubjectDAOJDBCImpl() throws SubjectException{
		File file=new File(DATA_STORE_FILE_NAME);
		
		if(!file.exists()) {
			subjects=new TreeMap<Long, Subject>();
			logger.info("Subject Map is newly instantiated");
		} else {
			try (ObjectInputStream fin=new ObjectInputStream(
					new FileInputStream(DATA_STORE_FILE_NAME))) {
				Object obj=fin.readObject();
				if(obj instanceof Map) {
					subjects= (Map<Long, Subject>) obj;
					logger.info("Book successfully loaded");
				} else {
					throw new SubjectException("Not a valid datastore");
				}
			} catch(IOException | ClassNotFoundException exp) {
				logger.error(exp);
				throw new SubjectException("Loading data failed");
			}
		}
	}

	@Override
	public Long add(Subject sub) throws SubjectException {
		Long subId=null;
		if(sub!=null) {
			subId=sub.getSubjectId();
			subjects.put(subId, sub);
			logger.info("Added subject"+sub);			
		}
		return subId;
	}

	@Override
	public boolean delete(Long subjectId) throws SubjectException{
		boolean isDone=false;
		if(subjectId!= null) {
			subjects.remove(subjectId);
			isDone=true;
			logger.info(subjectId + "Subject is deleted successfully");
		}
		
		return isDone;
	}

	@Override
	public Subject get(Long subjectId) throws SubjectException{
		return subjects.get(subjectId);
	}

	public void save() throws SubjectException{
		try(ObjectOutputStream fout=new ObjectOutputStream(
				new FileOutputStream(DATA_STORE_FILE_NAME))) {
			fout.writeObject(subjects);
			logger.info("Subject written into file");			
		} catch (IOException exp) {
			logger.error(exp);
			throw new SubjectException("Writing Data Failed");			
		}
				
	}
	
	public Long addBook(Subject sub, Book b) {
		Long subId=null;
		Long bookId=null;
		if(sub!=null) {
			subId=sub.getSubjectId();
			//bookId=sub.getBooks();
			subjects.put(subId, sub);
			logger.info("Added Book"+bookId);
		}
		return subId;
		
	}

	@Override
	public List<Subject> getAll() throws SubjectException {
		return new ArrayList<Subject>(subjects.values());
	}

}
