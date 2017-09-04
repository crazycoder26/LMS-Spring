package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> implements ResultSetExtractor<List<Publisher>>{

	@Autowired
	BookDAO bookDao;
	
	public void addPublisher(Publisher publisher) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		template.update("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values(?, ?, ?)", new Object[]{publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()});	
	}

	public void updatePublisher(Publisher publisher) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		template.update("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?", 
				new Object[]{publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId()});
	}
	
	public void deletePublisher(Publisher publisher) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		template.update("delete from tbl_publisher where publisherId = ?", new Object[]{publisher.getPublisherId()});
	}
	
	public List<Publisher> readAllPublishers(Integer pageNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return template.query("select * from tbl_publisher", this);
		
	}
	
	public Integer getCountOfPublishers() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		return template.queryForObject("select count(*) COUNT from tbl_publisher", Integer.class);
	}
	
	public Publisher readPublisherByPK(Integer publisherId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		List<Publisher> publishers =  template.query("select * from tbl_publisher where publisherId = ?", new Object[]{publisherId}, this);
		if(!publishers.isEmpty()){
			return publishers.get(0);
		}
		return null;
	}
	
	public Publisher readPublisherByID(Integer PubId) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		@SuppressWarnings("unchecked")
		List<Publisher> listPublisher = template.query("select * from tbl_publisher where publisherId = ?", new Object[] { PubId }, this);
		if (listPublisher != null && !listPublisher.isEmpty()) {
			return listPublisher.get(0);
		}
		return null;
	}
	
	
	@Override
	public List<Publisher> extractData(ResultSet rs)
			throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()){
			Publisher publisher = new Publisher();
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPublisherPhone(rs.getString("publisherPhone"));
			List<Book> books = bookDao.readBooksByPublisher(publisher.getPublisherId());
			publisher.setBooks(books);
			publishers.add(publisher);
		}
		return publishers;
	}

	
	public List<Publisher> readPublishersByName(String publisherName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		publisherName = "%"+publisherName+"%";
		return template.query("select * from tbl_publisher where publisherName LIKE  ?", new Object[]{publisherName}, this);
	}
}