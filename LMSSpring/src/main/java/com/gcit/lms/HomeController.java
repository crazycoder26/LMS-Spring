//package com.gcit.lms;
//
//import java.text.DateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
///**
// * Handles requests for the application home page.
// */
//@Controller
//public class HomeController {
//	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	
//	/**
//	 * Simply selects the home view to render by returning its name.
//	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {		
//		return "welcome";
//	}
//	
//// <----------------------------------------Admin Mapping--------------------------------------------------->	
//	
//	@RequestMapping(value = "/admin", method = RequestMethod.GET)
//	public String admin(Locale locale, Model model) {		
//		return "admin";
//	}
//	
//// <----------------------------------------Admin/Author Mapping-------------------------------------------->	
//	@RequestMapping(value = "/author", method = RequestMethod.GET)
//	public String author(Locale locale, Model model) {		
//		return "author";
//	}
//	
//	@RequestMapping(value = "/addauthor", method = RequestMethod.GET)
//	public String addAuthor(Locale locale, Model model) {		
//		return "addauthor";
//	}
//	
//	@RequestMapping(value = "/viewauthors", method = RequestMethod.GET)
//	public String viewAuthors(Locale locale, Model model) {		
//		return "viewauthors";
//	}
//	
//	
//// <----------------------------------------Admin/Book Mapping----------------------------------------------->		
//	@RequestMapping(value = "/book", method = RequestMethod.GET)
//	public String book(Locale locale, Model model) {		
//		return "book";
//	}
//	
//	@RequestMapping(value = "/viewbooks", method = RequestMethod.GET)
//	public String viewBooks(Locale locale, Model model) {		
//		return "viewbooks";
//	}
//	
//	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
//	public String addBooks(Locale locale, Model model) {		
//		return "addbook";
//	}
//	
//
//// <----------------------------------------Admin/Publisher Mapping------------------------------------------>	
//	@RequestMapping(value = "/publisher", method = RequestMethod.GET)
//	public String publisher(Locale locale, Model model) {		
//		return "publisher";
//	}
//	
//	@RequestMapping(value = "/addPublisher", method = RequestMethod.GET)
//	public String addPublisher(Locale locale, Model model) {		
//		return "addPublisher";
//	}
//	
//	@RequestMapping(value = "/viewPublisher", method = RequestMethod.GET)
//	public String viewPublisher(Locale locale, Model model) {		
//		return "viewPublisher";
//	}
//
//// <----------------------------------------Admin/Borrower Mapping------------------------------------------->	
//	@RequestMapping(value = "/borrower", method = RequestMethod.GET)
//	public String borrower(Locale locale, Model model) {		
//		return "borrower";
//	}
//	
//	@RequestMapping(value = "/addBorrower", method = RequestMethod.GET)
//	public String addBorrower(Locale locale, Model model) {		
//		return "addBorrower";
//	}
//	
//	@RequestMapping(value = "/viewBorrower", method = RequestMethod.GET)
//	public String viewBorrower(Locale locale, Model model) {		
//		return "viewBorrower";
//	}
//	
//	
//// <----------------------------------------Admin/Override Mapping------------------------------------------>	
//	@RequestMapping(value = "/override", method = RequestMethod.GET)
//	public String override(Locale locale, Model model) {		
//		return "override";
//	}
//	
//}
