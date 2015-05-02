package bl;

import com.itechart.training.tsvilik.contactsapp.entities.Contact;
import com.itechart.training.tsvilik.contactsapp.entities.SearchData;

import java.util.Date;
import java.util.List;

public interface ContactManager extends EntityManager<Contact, Integer> {

	List<Contact> getBatch(int batchSize, int batchNumber) throws ModelException;
	
	List<Contact> search(SearchData data, int batchSize, int batchNumber) throws ModelException;
	
	int getSearchResultsCount(SearchData data) throws ModelException;
	
	List<Contact> getByBirthday(Date birthday) throws ModelException;
}
