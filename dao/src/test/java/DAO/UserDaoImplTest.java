package DAO;

import beans.User;
import impl.UserDaoImpl;
import exception.DataAccessException;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.sql.DataSource;
import static org.junit.Assert.*;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Test class to work with User bean and mysql database
 */

public class UserDaoImplTest {

	private static UserDaoImpl dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataSource ds = new TestDataSource();
		dao = new UserDaoImpl(ds);
	}

	@Test
	public void test() {
		User user = new User();
		User user2 = null;

		user.setEmail("email@mail.com");
		user.setRole("user");
		user.setPass("123");
		
		try {
			user = dao.add(user);
			user2 = dao.getByKey(user.getId());
		} catch (DataAccessException e) {
			fail(e.getMessage());
		}
		assertTrue(user.getPass().equals(user2.getPass()));
		
		try {
			dao.delete(user.getId());
			assertNull(dao.getByKey(user.getId()));
		} catch (DataAccessException e) {
			fail(e.getMessage());
		}
	}

}
