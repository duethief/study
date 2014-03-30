package study.hibernate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.MetadataSourceType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CommDao;
import com.bookstore.domain.BookStatus;
import com.bookstore.domain.HistoryActionType;
import com.bookstore.domain.UserLevel;
import com.bookstore.entities.orm.Book;
import com.bookstore.entities.orm.History;
import com.bookstore.entities.orm.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/hibernateContext.xml")
@Transactional
public class HibernateTest {
	@Autowired
	private ApplicationContext context;
	
	@Test
	public void connect() {
		Properties properties = new Configuration().configure().getProperties();
		Set<Object> keys = properties.keySet();
		Iterator<Object> iter = keys.iterator();
		while(iter.hasNext()) {
			String key = (String) iter.next();
			System.out.println(key + "\t: " + properties.getProperty(key));
		}
		
		LocalSessionFactoryBean sf;
		sf.
		//System.out.println(conf.DEFAULT_ARTEFACT_PROCESSING_ORDER.toString());
	}
}
