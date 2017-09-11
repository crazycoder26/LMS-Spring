package com.gcit.lms.service;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;

@RestController
public class BorrowerService {
	
	
	@Autowired
	BorrowerDAO borrowerDao;
	
	@Autowired
	BookDAO bookDao;
	
	@Autowired
	BookLoansDAO bLoansDao;
	
	@Autowired
	BookCopiesDAO bCopiesDao;
	
	
	@RequestMapping(value = "/initBorrower", method = RequestMethod.GET, produces="application/json")
	public Borrower initBorrower() throws SQLException{
		return new Borrower();
	}
	
	@RequestMapping(value = "/validateCard/{cardNo}", method = RequestMethod.GET, produces = "application/json")
	public boolean validateCardNo(@PathVariable Integer cardNo){
		try{
			Borrower b = borrowerDao.readBorrowerByPK(cardNo);
			if(b.getCardNo() != null){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public String getBorrowerName(Integer cardNo) throws SQLException{
		try {
			Borrower borrower = borrowerDao.readBorrowerByPK(cardNo);
			if(borrower.getName() != null){
				return borrower.getName();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@RequestMapping(value = "/viewBookLoans/{pageNo}/{cardNo}", method = RequestMethod.GET, produces = "application/json")
	public List<BookLoans> getAllBookWithLoans(@PathVariable Integer pageNo, @PathVariable Integer cardNo) throws SQLException{
		try {
			List<BookLoans> loans = bLoansDao.readAllbooksWithCardNo(pageNo, cardNo);
			for(BookLoans l : loans){
				l.setBook(bookDao.readBookByPK(l.getBookId()));
			}
			return loans;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@RequestMapping(value = "/checkOut/{cardNo}/{bookId}/{branchId}", method = RequestMethod.GET, produces = "application/json")
	public BookLoans checkOut(@PathVariable Integer cardNo, @PathVariable Integer bookId, @PathVariable Integer branchId) throws SQLException{
		 BookLoans bookLoans = new BookLoans();
		try {
			long millis = System.currentTimeMillis();  
		    Date dateOut = new Date(millis);
		    long ltime = dateOut.getTime()+5*24*60*60*1000;
		    Date dueDate = new Date(ltime);
//			save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) values (?, ?, ?, ?, ?, ?)"
//					,new Object[]{book.getBookId(), branch.getBranchId(), borrower.getCardNo(), dateOut,dueDate, null});
		   
		    bookLoans.setBookId(bookId);
		    bookLoans.setBranchId(branchId);
		    bookLoans.setCardNo(cardNo);
		    bookLoans.setDateOut(dateOut);
		    bookLoans.setDueDate(dueDate);
		    bookLoans.setDateIn(null);
		    bLoansDao.addLoans(bookLoans);
		  
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBookId(bookId);
			bookCopies.setBranchId(branchId);
			//bookCopies.setNoOfCopies(bookCopies.getNoOfCopies() - 1);
			bCopiesDao.updateBookCopiesOut(bookCopies);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return bookLoans;
	}
	
	@RequestMapping(value = "/checkIn/{bookId}/{branchId}/{cardNo}", method = RequestMethod.GET, produces = "application/json")
	public BookLoans checkIn(@PathVariable Integer bookId, @PathVariable Integer branchId, @PathVariable Integer cardNo) throws SQLException{
		 BookLoans bookLoans = new BookLoans();
		try {
		    bookLoans.setBookId(bookId);
		    bookLoans.setBranchId(branchId);
		    bookLoans.setCardNo(cardNo);
		    bLoansDao.updateLoans(bookLoans);
		  
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBookId(bookId);
			bookCopies.setBranchId(branchId);
			bCopiesDao.updateBookCopiesIn(bookCopies);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return bookLoans;
	}
	
}
