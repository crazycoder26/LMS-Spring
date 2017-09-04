package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
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
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

@RestController
public class AdminService {

	DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
	
	@Autowired
	AuthorDAO authorDao;
	
	@Autowired
	BookDAO bookDao;
	
	@Autowired
	BorrowerDAO borrowerDao;
	
	@Autowired
	BookLoansDAO bLoansDao;
	
	@Autowired
	BookCopiesDAO bCopiesDao;
	
	@Autowired
	PublisherDAO publisherDao;
	
	@Autowired
	BranchDAO branchDao;
	
	@Autowired
	GenreDAO genreDao;
	
	
	
// <----------------------------------------------- Author Transactions -------------------------------------------------->	
	
	@RequestMapping(value = "/addAuthor", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void addAuthor(@RequestBody Author author) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
			if(author.getAuthorId()!=null){
				authorDao.updateAuthor(author);
			}else{
				authorDao.addAuthor(author);
			}
	}
	
	
	public Author getAuthorByPK(Integer authorId) throws SQLException{
		try {
			return authorDao.readAuthorByPK(authorId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@RequestMapping(value = "/viewAuthors/{pageNo}", method = RequestMethod.GET, produces="application/json")
	public List<Author> getAllAuthors(@PathVariable int pageNo) throws SQLException{
		try {
			return authorDao.readAllAuthors(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	
	public List<Author> getAuthorsByName(String authorName, Integer pageNo) throws SQLException{
		try {
			return authorDao.readAuthorsByName(authorName, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.DELETE, consumes="application/json")
	@Transactional
	public void deleteAuthor(@RequestBody Author author) throws SQLException {
		try {
			authorDao.deleteAuthor(author);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void deleteBookAuthor(Integer bookId) throws SQLException {
		try {
			authorDao.deleteBookAuthor(bookId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public Integer getAuthorsCount(String searchString) throws SQLException{
		 
		try {
			return authorDao.getCountOfAuthors(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
// <----------------------------------------------- Author Transactions End ----------------------------------------------->
	
	
// <----------------------------------------------- Book Transactions------------------------------------------------------>
	
	@RequestMapping(value = "/deleteBook", method = RequestMethod.DELETE, consumes="application/json")
	@Transactional
	public void deleteBook(@RequestBody Book book) throws SQLException {
		try {
			bookDao.deleteBook(book);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void addBook(@RequestBody Book book) throws SQLException{		 
		try {
			if(book.getBookId() != null){
				bookDao.updateBook(book);
			}else{
				bookDao.addBook(book);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public Book getBookByPK(Integer bookId) throws SQLException{
		 
		try {
			return bookDao.readBookByPK(bookId);  
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/saveBook", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void saveBook(@RequestBody Book book) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			if (book.getBookId() != null) {
				bookDao.updateBook(book);
			} else {
				//bdao.addBook(book);
				Integer bookId = bookDao.addBookWithID(book);
				
				if(book.getAuthors()!=null && !book.getAuthors().isEmpty()){
					for(Author a: book.getAuthors()){
						bookDao.addBookAuthors(bookId, a.getAuthorId());
					}
				}
				if(book.getGenres()!=null && !book.getGenres().isEmpty()){
					for(Genre a: book.getGenres()){
						bookDao.addBookGenres(bookId, a.getGenreId());
					}
				}
				if(book.getPublisher()!=null) {
					//System.out.println("Pub Id: "+ book.getPublisher().getPublisherId());
					bookDao.updateBookPublisher(bookId,book.getPublisher().getPublisherId());
				}
			}
			  
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "/viewBooks/{pageNo}", method = RequestMethod.GET, produces="application/json")
	public List<Book> getAllBooks(@PathVariable int pageNo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{ 
		try {
			return bookDao.readAllBooks(1);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public List<Book> getBooksByName(String bookName) throws SQLException{
		 
		try {
			return bookDao.readBooksByName(bookName);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	
	public Integer getBooksCount() throws SQLException{
		try { 
			return bookDao.getCountOfBooks(); // I was here : go to bookDao //
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
// <----------------------------------------------- Book Transactions End------------------------------------------------------>	
	
	
// <----------------------------------------------- Borrower Transactions------------------------------------------------------>
	
	@RequestMapping(value = "/addBorrower", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void addBorrower(@RequestBody Borrower borrower) throws SQLException{
		try {
			if(borrower.getCardNo() != null){
				borrowerDao.updateBorrower(borrower);
			}else{
				borrowerDao.addBorrower(borrower);
			}
			  
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public Borrower getBorrowerByPK(Integer cardNo) throws SQLException{
		try {
			return borrowerDao.readBorrowerByPK(cardNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/viewBorrowers/{pageNo}", method = RequestMethod.GET, produces="application/json")
	public List<Borrower> getAllBorrowers(@PathVariable int pageNo) throws SQLException{
		 
		try {
			return borrowerDao.readAllborrowers(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public List<Borrower> getBorrowersByName(String borrowersName) throws SQLException{
		 
		try {
			return borrowerDao.readBorrowersByName(borrowersName);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public Integer getBorrowersCount() throws SQLException{
		 
		try {
			return borrowerDao.getCountOfBorrowers();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	@RequestMapping(value = "/deleteBorrower", method = RequestMethod.DELETE, consumes="application/json")
	@Transactional
	public void deleteBorrower(@RequestBody Borrower borrower) throws SQLException {
		try {
			borrowerDao.deleteBorrower(borrower);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
// <----------------------------------------------- Borrower Transactions End------------------------------------------------------>	
	

// <----------------------------------------------- Loan Transactions-------------------------------------------------------------->	
	@RequestMapping(value = "/overrideLoans", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void overrideDate(@RequestBody BookLoans loans) throws SQLException{
		try {
			if(loans.getDueDate() != null){
				bLoansDao.updateLoansDue(loans);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "/viewLoans/{pageNo}", method = RequestMethod.GET, produces="application/json")
	public List<BookLoans> getAllBookLoans(@PathVariable int pageNo) throws SQLException{
		try {
			return bLoansDao.readAllOverrideLoans(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public BookLoans getBooKLoansByPK(DateTime dateOut) throws SQLException{
		
		try {
			
			return bLoansDao.readBookLoansByPK(dateOut);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

// <----------------------------------------------- Loan Transactions End------------------------------------------------------>	
	
	
	
// <----------------------------------------------- Publisher Transactions------------------------------------------------------>	
	
	// Add publisher //
	@RequestMapping(value = "/addPublisher", method = RequestMethod.POST, consumes="application/json")
	@Transactional
	public void addPublisher(@RequestBody Publisher publisher) throws SQLException{ 
		try {
			if(publisher.getPublisherId() != null){
				publisherDao.updatePublisher(publisher);
			}else{
				publisherDao.addPublisher(publisher);
			}
			  
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public Publisher getPublisherById(Integer pubId) throws SQLException{
		try{
			return publisherDao.readPublisherByID(pubId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/viewPublishers/{pageNo}", method = RequestMethod.GET, produces="application/json")
	public List<Publisher> getAllPublishers(@PathVariable int pageNo) throws SQLException{
		try {
			return publisherDao.readAllPublishers(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public List<Publisher> getPublishersByName(String publisherName) throws SQLException{
		try {
			return publisherDao.readPublishersByName(publisherName);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public Integer getPublishersCount() throws SQLException{
		try {
			return publisherDao.getCountOfPublishers();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	@RequestMapping(value = "/deletePublisher", method = RequestMethod.DELETE, consumes="application/json")
	@Transactional
	public void deletePublisher(@RequestBody Publisher publisher) throws SQLException {
		try {
			publisherDao.deletePublisher(publisher);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public Publisher getPublisherByPK(Integer publisherId) throws SQLException{
		try {
			return publisherDao.readPublisherByPK(publisherId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
// <----------------------------------------------- Publisher Transactions End------------------------------------------------------>	


// <----------------------------------------------- Genre Transactions-------------------------------------------------------------->
	public Genre getGenreById(Integer genreId) throws SQLException{
		try{
			
			return genreDao.readGenreByID(genreId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Genre> getAllGenres() throws SQLException{
		try {
			return genreDao.readAllGenres();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
// <-----------------------------------------------Genre Transactions End--------------------------------------------------------->	
	
}
