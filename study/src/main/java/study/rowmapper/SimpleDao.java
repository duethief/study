package study.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bookstore.domain.BookStatus;
import com.bookstore.entities.Book;

@Repository
public class SimpleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private RowMapper<Book> getRowMapper() {
		return new RowMapper<Book>() {
			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setPublishDate(new java.util.Date(rs.getTimestamp("publishDate").getTime()));
				book.setComment(rs.getString("comment"));
				book.setStatus(BookStatus.valueOf(rs.getInt("status")));
				book.setRentUserId(rs.getObject("rentUserId", Integer.class));
				return book;
			}
		};
	}
	
	public Book getById(final int id) {
		//List<Book> books = this.jdbcTemplate.query("select id, name, author, publishDate, comment, status, rentUserId from books where id = ?", getRowMapper(), id);
		List<Book> books = this.jdbcTemplate.query("select id, name, author, publishDate, comment, status, rentUserId from books where id = ?", new BeanPropertyRowMapper<Book>(Book.class), id);
		
		return books.get(0);
	}
}
