package study.rowmapper;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.HistoryDao;
import com.bookstore.domain.BookStatus;
import com.bookstore.entities.Book;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/jdbcTemplateContext.xml")
public class RowMapperTest {
	@Autowired
	private ApplicationContext context;
	
	private SimpleDao simpleDao;
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
		
		simpleDao = context.getBean(SimpleDao.class);
	}
	
	@Test
	public void getBookbySimpleDao() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Book> books = getBooks();
		Book book = books.get(1);
		Book simpleBook = simpleDao.getById(book.getId());
		
		assertThat(book.getName(), is(simpleBook.getName()));
		assertThat(book.getAuthor(), is(simpleBook.getAuthor()));
		assertThat(book.getComment(), is(simpleBook.getComment()));
		assertThat(book.getPublishDate().toString(), is(simpleBook.getPublishDate().toString()));
	}
}

