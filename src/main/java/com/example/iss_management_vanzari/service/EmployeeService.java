package com.example.iss_management_vanzari.service;

import java.util.List;

import com.example.iss_management_vanzari.models.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class EmployeeService {

    private final SessionFactory sessionFactory;

    public EmployeeService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Employee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void update(Employee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Employee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Employee findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Employee> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public Employee getEmployeeByUsernameAndPassword(String username, String password) {
//        System.out.println("search employee" + username + " " + password + " ");
//        try (Session session = sessionFactory.openSession()) {
//            Query<Employee> query = session.createQuery("from Employee where username=:username and password=:password", Employee.class);
//            query.setParameter("username", username);
//            query.setParameter("password", password);
//            System.out.println("FOUND employees:" + query.list().size());
//            return query.list().get(0);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public Employee getEmployeeByUsernameAndPassword(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("from Employee where username=:username and password=:password", Employee.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            List<Employee> employees = query.getResultList();
            if (employees.size() > 0) {
                return employees.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
