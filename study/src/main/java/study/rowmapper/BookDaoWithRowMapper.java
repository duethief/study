package study.rowmapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bookstore.entities.orm.Book;

@Repository
public class BookDaoWithRowMapper {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Book> rowMapper;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public RowMapper<Book> getRowMapper() {
		return rowMapper;
	}
	
	public void setRowMapper(RowMapper<Book> rowMapper) {
		this.rowMapper = rowMapper;
	}
	
	public Book getById(int id) {
		List<Book> books = this.jdbcTemplate.query("select id, name, author, publishDate, comment, status, rentUserId from books where id = ?", rowMapper, id);
		
		return books.get(0);
	}
}
