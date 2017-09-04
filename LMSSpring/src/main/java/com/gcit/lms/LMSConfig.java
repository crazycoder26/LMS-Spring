package com.gcit.lms;

import javax.swing.plaf.basic.BasicScrollBarUI;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.PublisherDAO;

@Configuration
public class LMSConfig {

	public String driver = "com.mysql.cj.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost/Library?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EST";
	public String password = "";
	public String user = "root";
	
	@Bean
	public BasicDataSource basicDateSource(){
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(driver);
		bds.setUrl(url);
		bds.setPassword(password);
		bds.setUsername(user);
		return bds;
	}
	
	@Bean
	public JdbcTemplate template(){
		return new JdbcTemplate(basicDateSource());
	}
	
	@Bean
	public AuthorDAO aDao(){
		return new AuthorDAO();
	}
	
	@Bean
	public BookDAO bDao(){
		return new BookDAO();
	}
	
	@Bean
	public BookCopiesDAO bcDao(){
		return new BookCopiesDAO();
	}
	
	@Bean
	public BookLoansDAO blDao(){
		return new BookLoansDAO();
	}
	
	@Bean
	public BorrowerDAO brDao(){
		return new BorrowerDAO();
	}
	
	@Bean
	public BranchDAO bnDao(){
		return new BranchDAO();
	}
	
	@Bean
	public GenreDAO gDao(){
		return new GenreDAO();
	}
	
	@Bean
	public PublisherDAO bdao(){
		return new PublisherDAO();
	}
	

	@Bean
	public PlatformTransactionManager txManager(){
		return new DataSourceTransactionManager(basicDateSource());
	}
}
