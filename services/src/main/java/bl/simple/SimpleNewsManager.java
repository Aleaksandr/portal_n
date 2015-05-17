package bl.simple;

import beans.New;
import bl.AbstractEntityManager;
import bl.NewsManager;
import dao.INewsDao;
import exception.DataAccessException;
import exeption.ModelException;
import org.apache.log4j.Logger;
import java.util.Date;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class of News
 */

public class SimpleNewsManager extends AbstractEntityManager<New, Integer> implements NewsManager {

	protected static Logger logger = Logger.getLogger(SimpleNewsManager.class);

	@Override
	protected INewsDao getDao() {
		return DaoFactoryProvider.getFactory().getNewsDao();
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	public List<New> getByDate(Date date) throws ModelException, DataAccessException {
		return DaoFactoryProvider.getFactory().getNewsDao().getNewsByDate(date);
	}
}
