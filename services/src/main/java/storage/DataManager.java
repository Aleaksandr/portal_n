package storage;

import storage.entities.Product;
import storage.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class DataManager {

    public static ArrayList<Product> getProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Product");
        ArrayList<Product> list = (ArrayList<Product>)query.list();
        session.close();
        return list;
    }

    public static Product getProduct(Integer type, Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Product where id = :id and type = :type");
        query.setParameter("id", id);
        query.setParameter("type", type);
        Product product = (Product)query.uniqueResult();
        session.close();
        return product;
    }

    public static boolean buyProduct(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery("from Product where id = :id");
        query.setParameter("id", id);
        Product product = (Product)query.uniqueResult();
        if(product!=null && product.getAmount()>0) {
            product.setAmount(product.getAmount()-1);
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
            return true;
        }
        return false;
    }
}
