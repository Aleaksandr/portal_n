package bl;

import beans.Identifiable;
import exeption.ModelException;
import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * General interface of business logic
 */


public interface EntityManager<T extends Identifiable<K>, K> {
	List<T> getAll() throws ModelException;

	T getByKey(K key) throws ModelException;
	
	T save(T entity) throws ModelException;

	void remove(K key) throws ModelException;

	void update(T entity) throws ModelException;
	
	//int getCount() throws ModelException;
}
