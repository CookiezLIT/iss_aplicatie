package com.example.iss_management_vanzari.service;

import java.util.List;

import com.example.iss_management_vanzari.models.Manager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ManagerService {

    private final SessionFactory sessionFactory;

    public ManagerService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Manager manager) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(manager);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Manager manager) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(manager);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Manager manager) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(manager);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Manager findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Manager.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Manager> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Manager> query = session.createQuery("FROM Manager", Manager.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Manager getManagerByUsernameAndPassword(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            Query<Manager> query = session.createQuery("from Manager where username=:username and password=:password", Manager.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
