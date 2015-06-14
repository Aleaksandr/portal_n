package bl;

import exception.PersistException;
import pojos.News;
import exception.DataAccessException;
import exeption.ModelException;
import java.util.Date;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Interface of NewsManager
 */

public interface NewsManager extends EntityManager<News> {

	/** Gets the the appropriate List<News> by author value parameter */
	List<News> getNewsByAuthor(String author) throws DataAccessException;

	/** Gets the the appropriate List<News> by date value parameter */
	List<News> getNewsByDate(Date date);

	/** Gets the the appropriate News by title value parameter */
	News getNewsByTitle(String title) throws DataAccessException;

	/** Gets the the appropriate List<News> by period */
	public List<News> getNewsByPeriod(Integer first, Integer second) throws DataAccessException;
}
