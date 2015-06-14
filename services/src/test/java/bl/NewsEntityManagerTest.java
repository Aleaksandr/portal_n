package bl;

import bl.simple.SimpleNewsManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojos.Comment;
import pojos.News;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static util.TestConstants.TestConst.*;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * TESTS News Entity Manager
 */

public class NewsEntityManagerTest {

	private static Logger logger = Logger.getLogger(NewsEntityManagerTest.class);

	private Session session;
	private Transaction transaction;
	private SimpleNewsManager newsManager;
	Comment commentary;
	News news;

	public NewsEntityManagerTest() {
		newsManager = new SimpleNewsManager();
	}

	@Before
	public void setUp() throws Exception {
		List<Comment> testComList = new ArrayList<Comment>();

		commentary = new Comment();
		commentary.setUser_id(USERID);
		commentary.setDate(DATE);
		commentary.setComment(COMMENT);

		news = new News();
		news.setTitle(TITLE);
		news.setTitle4menu(TITLE4MENU);
		news.setDate(DATE);
		news.setAuthor(AUTHOR);
		news.setComments(testComList);
		news.setItem(ITEM);

		testComList.add(commentary);
		news.setComments(testComList);
		commentary.setNew(news);
		newsManager.save(news);

		session = newsManager.getSession();
	}

	@After
	public void tearDown() throws SQLException {
		//transaction.commit();
		newsManager.cleanSession(true);
		commentary = null;
		news = null;
	}
	@Test
	public void testGetByKey() throws Exception {
		Integer id = news.getId();
		News expected = newsManager.getByKey(id);
		logger.info("News object, what we get from database " + expected.getTitle() + " - " + expected.getItem());
		Assert.assertNotNull("After persist id is null.", expected);
	}

	@Test
	public void testUpdate() throws Exception {
		Integer id = news.getId();
		news.setTitle(UNIQUE_TITLE);
		newsManager.update(news);
		News expected =newsManager.getByKey(id);
		assertSame(expected, news);
		logger.info("Saved object " + news.getId() + " - " + news.getTitle());
		logger.info("Updated object " + expected.getId() + " - " + expected.getTitle());
	}

	@Test
	public void testDelete() throws Exception {
		assertNotNull(newsManager.getByKey(news.getId()).getId());
		newsManager.remove(news);
		news = newsManager.getByKey(news.getId());
		assertNull("Object is not deleted from database ", news);
	}

	@Test
	public void testLoadAll() throws Exception {
		List list = newsManager.loadAll();
		Assert.assertNotNull(list);
		Assert.assertTrue(list.size() > ZERO);
	}

	@Test
	public void getNewsByAuthor() throws Exception {

		String auth = news.getAuthor();
		List<News> expected = newsManager.getNewsByAuthor(auth);
		logger.info("List of news by personId " + expected.size());
		assertNotNull(expected.size());
	}

	@Test
	public void getNewsByTitle() throws Exception {
		String titl = news.getTitle();
		News expected = newsManager.getNewsByTitle(titl);
		logger.info("News id by Title " + expected.getId());
		assertNotNull(expected.getId());
	}
}
