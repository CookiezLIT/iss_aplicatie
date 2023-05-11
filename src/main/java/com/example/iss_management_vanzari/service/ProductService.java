package com.example.iss_management_vanzari.service;



import com.example.iss_management_vanzari.models.Employee;
import com.example.iss_management_vanzari.models.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductService {

    private final SessionFactory sessionFactory;

    public ProductService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        }
    }

    public void updateProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        }
    }

    public void deleteProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        }
    }

    public Product getProductById(int productId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, productId);
        }
    }

    public List<Product> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        }
    }

    public void subtractQuantityFromProduct(Long productId, int quantity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Product product = session.get(Product.class, productId);
            product.subtractQuantity(quantity);
            session.update(product);
            transaction.commit();
        }
    }

    public Product getProductByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Product> query = session.createQuery("FROM Product WHERE name = :name", Product.class);
            query.setParameter("name", name);
            List<Product> products = query.getResultList();
            if (products.size() > 0) {
                return products.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
