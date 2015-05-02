package bl;

import exeption.ModelException;

import java.util.List;

public interface EntityManager<T extends Identifiable<K>, K> {
	List<T> getAll() throws ModelException;

	T getByKey(K key) throws ModelException, ModelException;
	
	T save(T entity) throws ModelException;

	void remove(K key) throws ModelException;

	void update(T entity) throws ModelException;
	
	int getCount() throws ModelException;
}
