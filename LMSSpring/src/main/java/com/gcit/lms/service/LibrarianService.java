package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.LibraryBranch;
import com.gcit.lms.entity.Publisher;

@RestController
public class LibrarianService {
	
	@Autowired
	AuthorDAO authorDao;
	
	@Autowired
	BookDAO bookDao;
	
	@Autowired
	BorrowerDAO borrowerDao;
	
	@Autowired
	BookCopiesDAO bCopiesDao;
	
	@Autowired
	PublisherDAO publisherDao;
	
	@Autowired
	BranchDAO branchDao;
	
	@RequestMapping(value = "/viewBranches/{pageNo}", method = RequestMethod.GET, produces = "application/json")
	public List<LibraryBranch> getAllBranches(@PathVariable int pageNo) throws SQLException{
		try {
			return branchDao.readAllBranches(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public BookCopies getBookCopiesById(Integer bookId, Integer branchId) throws SQLException{
		try {
			return bCopiesDao.getAllCopiesbId(bookId,branchId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public String getBorrowerName(Integer cardNo) throws SQLException{
		try {
			Borrower borrower = borrowerDao.readBorrowerByPK(cardNo);
			if(borrower != null){
				return borrower.getName();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@RequestMapping(value = "/viewBranchBooks/{pageNo}/{branchId}", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getAllBooksWithBranch(@PathVariable Integer pageNo, @PathVariable Integer branchId) throws SQLException{
		
		try {
			return bookDao.readAllbooksWithBranch(pageNo, branchId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	
	public LibraryBranch getBranchPK(Integer branchId) throws SQLException{
		try {
			return branchDao.readBranchByPK(branchId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	@RequestMapping(value = "/addOrUpdateBranch", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void addBranch(@RequestBody LibraryBranch branch) throws SQLException{
		try {
			if(branch.getBranchId()!=null){
				branchDao.updateBranch(branch);
			}else{
				branchDao.addBranch(branch);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/updateCopies", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public void updateCopies(@RequestBody BookCopies copies) throws SQLException{
		try {
			if(copies.getBookId() != null){
				bCopiesDao.updateBookCopies(copies);
			}else{
				bCopiesDao.addBookCopies(copies);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}	
}
