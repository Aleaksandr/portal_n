package bl;

import beans.New;
import exeption.ModelException;

import java.util.Date;
import java.util.List;


public interface NewsManager extends EntityManager<New, Integer> {

	List<New> getByDate(Date date) throws ModelException;

}
