package bl;

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

	List<News> getByDate(Date date) throws ModelException, DataAccessException;

}
