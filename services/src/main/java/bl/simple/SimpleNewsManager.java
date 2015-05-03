package bl.simple;

import beans.New;
import beans.SearchData;
import bl.AbstractEntityManager;
import bl.NewsManager;
import dao.dao.GenericDao;
import dao.dao.INewsDao;
import exeption.DataAccessException;
import exeption.ModelException;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

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
	public List<New> getByDate(Date date) throws ModelException {
		return null;
	}
}
