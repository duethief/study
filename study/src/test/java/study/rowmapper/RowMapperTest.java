package study.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import com.bookstore.dao.BookDao;
import com.bookstore.dao.HistoryDao;
import com.bookstore.domain.BookStatus;
import com.bookstore.entities.orm.Book;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * RowMapper 학습용
 * @author InSeok
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/jdbcTemplateContext.xml")
public class RowMapperTest {
	@Autowired
	private ApplicationContext context;
	
	private BookDaoWithRowMapper bookDaoWithRowMapper;
	private BookDao bookDao;
	private HistoryDao historyDao;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private List<Book> getBooks() {
		Book book1 = new Book();
		book1.setId(1);
		book1.setName("book name01");
		book1.setAuthor("author name 01");
		book1.setComment("comment01");
		book1.setPublishDate(new Date());
		book1.setStatus(BookStatus.CanRent);
		book1.setRentUserId(null);
		
		Book book2 = new Book();
		book2.setId(2);
		book2.setName("book name02");
		book2.setAuthor("author name 02");
		book2.setComment("comment02");
		book2.setPublishDate(new Date());
		book2.setStatus(BookStatus.CanRent);
		
		Book book3 = new Book();
		book3.setId(3);
		book3.setName("book name03");
		book3.setAuthor("author name 03");
		book3.setComment("comment03");
		book3.setPublishDate(new Date());
		book3.setStatus(BookStatus.CanRent);
		
		List<Book> books = Arrays.asList(book1, book2, book3);
		return books;
	}
	
	@Before
	public void setUp() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		historyDao = context.getBean(HistoryDao.class);
		
        historyDao.deleteAll();
        assertThat(historyDao.countAll(), is(0));
        
		bookDao = context.getBean(BookDao.class);
		
		bookDao.deleteAll();
		assertThat(bookDao.countAll(), is(0));
		
		List<Book> books = getBooks();
		for(Book book : books) {
			bookDao.add(book);
		}
		assertThat(bookDao.countAll(), is(books.size()));
		
		bookDaoWithRowMapper = context.getBean(BookDaoWithRowMapper.class);
	}
	
	private void compareBook() {
		List<Book> books = getBooks();
		Book book1 = books.get(1);
		Book book2 = bookDaoWithRowMapper.getById(book1.getId());
		
		assertThat(book1.getName(), is(book2.getName()));
		assertThat(book1.getAuthor(), is(book2.getAuthor()));
		assertThat(book1.getComment(), is(book2.getComment()));
		assertThat(book1.getPublishDate().toString(), is(book2.getPublishDate().toString()));
		assertThat(book1.getStatus().intValue(), is(book2.getStatus().intValue()));
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
	
	/**
	 * RowMapper를 직접 정의해서 사용
	 */
	@Test
	@DirtiesContext
	public void testCustomRowMapper() {
		bookDaoWithRowMapper.setRowMapper(getRowMapper());
		
		compareBook();
	}
	
	/**
	 * BeanPropertyRowMapper를 사용
	 * enum을 사용할 경우 사용 불가
	 */
	@Test
	@DirtiesContext
	public void testBeanPropertyRowMapper() {
		bookDaoWithRowMapper.setRowMapper(new BeanPropertyRowMapper<Book>(Book.class));
		
		compareBook();
	}
	
//	ColumnMapRowMapper는 row를 Bean이 아니라 Map으로 사용할 때만 쓰는 RowMapper
//	@Test
//	@DirtiesContext
//	public void testColumnMapRowMapper() {
//		bookDaoWithRowMapper.setRowMapper(new ColumnMapRowMapper<Book>());
//		
//		compareBook();
//	}
	
	/**
	 * ParameterizedRowMapper는 RowMapper의 타입 선언을 간단하게하는 newInstance를 추가하는 interface이나 Spring 3.0부터 RowMapper에서 기본 포함하므로 쓸 필요가 없어졌음
	 */
	@Test
	@DirtiesContext
	public void testParameterizedBeanPropertyRowMapper() {
		bookDaoWithRowMapper.setRowMapper(ParameterizedBeanPropertyRowMapper.newInstance(Book.class));
		
		compareBook();
	}
}

