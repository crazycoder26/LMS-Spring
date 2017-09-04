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
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;

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
	BookLoansDAO bLoansDao;
	
	@Autowired
	BookCopiesDAO bCopiesDao;
	
	
	
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
			return bLoansDao.readAllbooksWithCardNo(pageNo, cardNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@RequestMapping(value = "/checkOut", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void checkOut(@RequestBody Borrower borrower, @RequestBody Book book, @RequestBody LibraryBranch branch) throws SQLException{
		try {
			long millis = System.currentTimeMillis();  
		    Date dateOut = new Date(millis);
		    long ltime = dateOut.getTime()+5*24*60*60*1000;
		    Date dueDate = new Date(ltime);
//			save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) values (?, ?, ?, ?, ?, ?)"
//					,new Object[]{book.getBookId(), branch.getBranchId(), borrower.getCardNo(), dateOut,dueDate, null});
		    BookLoans bookLoans = new BookLoans();
		    bookLoans.setBookId(book.getBookId());
		    bookLoans.setBranchId(branch.getBranchId());
		    bookLoans.setCardNo(borrower.getCardNo());
		    bookLoans.setDateOut(dateOut);
		    bookLoans.setDueDate(dueDate);
		    bookLoans.setDateIn(null);
		    bLoansDao.addLoans(bookLoans);
		  
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBookId(book.getBookId());
			bookCopies.setBranchId(branch.getBranchId());
			//bookCopies.setNoOfCopies(bookCopies.getNoOfCopies() - 1);
			bCopiesDao.updateBookCopiesOut(bookCopies);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "/checkIn", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void checkIn(@RequestBody Borrower borrower, @RequestBody Book book,  @RequestBody LibraryBranch branch) throws SQLException{
		try {
			long millis = System.currentTimeMillis();  
		    Date dateIn = new Date(millis);
		    BookLoans bookLoans = new BookLoans();
		    bookLoans.setBookId(book.getBookId());
		    bookLoans.setBranchId(branch.getBranchId());
		    bookLoans.setCardNo(borrower.getCardNo());
		    bookLoans.setDateOut(bookLoans.getDateOut());
		    bookLoans.setDueDate(bookLoans.getDueDate());
		    bookLoans.setDateIn(dateIn);
		    bLoansDao.updateLoans(bookLoans);
		  
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBookId(book.getBookId());
			bookCopies.setBranchId(branch.getBranchId());
			bCopiesDao.updateBookCopiesIn(bookCopies);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
}
