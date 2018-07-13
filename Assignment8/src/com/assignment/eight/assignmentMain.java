package com.assignment.eight;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.assignment.eight.dao.SubjectDAOSerializationImpl;
import com.assignment.eight.dto.Book;
import com.assignment.eight.dto.Menu;
import com.assignment.eight.dto.Subject;
import com.assignment.eight.exception.SubjectException;
import com.assignment.eight.service.ISubjectService;
import com.assignment.eight.service.SubjectServiceImpl;

public class assignmentMain {
	
	static Scanner scan;
	static ISubjectService subjService;
	static DateTimeFormatter dtFormater;
	
	public static void main(String[] args) {
		
		try {
			subjService=new SubjectServiceImpl();
		} catch(SubjectException exp) {
			System.err.println(exp.getMessage());
			System.exit(0);
		}		
		
		scan = new Scanner(System.in);
		dtFormater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		Menu menu = null;

		while (menu != Menu.QUIT) {

			System.out.println("Choice\tMenu Item");
			System.out.println("===========================");
			for (Menu m : Menu.values()) {
				System.out.println(m.ordinal() + "\t" + m.name());
			}
			System.out.print("Choice: ");
			int choice = -1;
			if (scan.hasNextInt())
				choice = scan.nextInt();
			else {
				scan.next();
				System.out.println("Pleae choose a choice displayed");
				continue;
			}

			if (choice < 0 || choice >= Menu.values().length) {
				System.out.println("Invlaid Choice");
			} else {
				menu = Menu.values()[choice];

				switch (menu) {
				case ADDBOOK:
					addBook();
					break;
				case ADDSUBJECT:
					addSubject();
					break;
				case DELETESUBJECT:
					deleteSubject();
					break;
				case DELETEBOOK:
					deleteBook();
					break;
				case SEARCHBOOK:
					searchBook();
					break;
				case SEARCHSUBJECT:
					searchSubject();
					break;	
				case QUIT:
					System.out.println("ThanQ Come Again!");
					break;
				}
			}

		}
		scan.close();
		
		if(((SubjectServiceImpl)subjService).getDAO() instanceof SubjectDAOSerializationImpl) {
			try {
				((SubjectDAOSerializationImpl)((SubjectServiceImpl)subjService).getDAO()).save();
			} catch(SubjectException e) {
				e.printStackTrace();
			}
		}
	}
	
	static void searchSubject() {
		System.out.println("Subject Id");
		Long subjId=scan.nextLong();
		try {
			Subject s=subjService.get(subjId);
			if(s != null)
				System.out.println(s);
			else
				System.out.println("No such Subjects found");
		} catch(SubjectException ex) {
			System.out.println(ex.getMessage());
		}					
	}

	static void searchBook() {
				
	}

	static void deleteBook() {
		//
	}

	static void deleteSubject() {
		System.out.println("Subject Id");
		Long subjId=scan.nextLong();
		List<Subject> sub;
		try {
			sub=subjService.getAll();
			if(sub.size()==0) {
				System.out.println("No Subjects are there to delete");
			} else
				System.out.println("Please select following subjects to delete");
				for(Subject subj : sub)
					System.out.println(subj.getSubjectId()+"\t");
			boolean isDone=subjService.delete(subjId);
			if(isDone)
				System.out.println("Subject and its associated books or deleted");
			else
				System.out.println("No such Subjects found");
		} catch(SubjectException ex) {
			System.out.println(ex.getMessage());
		}	
					
	}

	static void addSubject() {
		try {
			Subject s=new Subject();
		    System.out.print("Subject Id: ");
		    s.setSubjectId(scan.nextLong());
		    System.out.print("Subject Title: ");
		    s.setSubtitle(scan.next());
		    System.out.print("Duration in Hours: ");
		    s.setDurationInHours(scan.nextInt());
		    Long subjId = subjService.add(s);
			System.out.println("Subject is Added: " + subjId);
		    } catch(SubjectException exp) {
			System.out.println(exp.getMessage());		
		}
	}

	static void addBook() {
		Book b=new Book();
		Subject s=new Subject();
		boolean isPresent=false;
		List<Subject> sub;
	    try {
	    	sub=subjService.getAll();
			if(sub.size()==0) {
				System.out.println("No Subjects are there to create book. Please add book first");
			} else
				System.out.println("Please select following anyone subject to make reference");
				for(Subject subj : sub)
					System.out.println(subj.getSubjectId()+"\t");	    	
	    } catch(SubjectException e) {
	    	System.out.println(e.getMessage());
	    }
	    Long subId=scan.nextLong();
	    try {
	    	sub=subjService.getAll();
	        for(Subject subj : sub) {
	    	   if(subId==subj.getSubjectId()) {
	    		  isPresent=true;
	    	      break;}
	    	   else
	    		  isPresent=false;
	         }
	    } catch(SubjectException e) {
	    	System.out.println(e.getMessage());
	    }
		System.out.print("Book Id: ");
		b.setBookId(scan.nextLong());
		System.out.print("Book Title: ");
		b.setTitle(scan.next());
		System.out.print("Book Price: ");
		b.setPrice(scan.nextDouble());
		System.out.print("Book Volume: ");
		b.setVolume(scan.nextInt());
		System.out.print("Book publishDate(dd-MM-yyyy): ");
		String pubDtStr = scan.next();
		b.setPublishDate(LocalDate.parse(pubDtStr, dtFormater));
		subjService.addBook(sub, b);
	}

}
