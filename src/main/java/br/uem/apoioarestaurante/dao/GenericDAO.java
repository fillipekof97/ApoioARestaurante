package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class GenericDAO<T> {

    public T insert(T t) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
        } catch (RuntimeException rex) {
            rex.printStackTrace();
            return null;
        }
        return t;
    }

    public boolean update(T t) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
        } catch (RuntimeException rex) {
            rex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(T t) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
        } catch (RuntimeException rex) {
            rex.printStackTrace();
            return false;
        }
        return true;
    }

    public List<T> listAll(Class<T> classe) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String query = "from " + classe.getSimpleName();
            return session.createQuery(query).getResultList();
        } catch (RuntimeException rex) {
            rex.printStackTrace();
        }
        return null;
    }

    public T findById(Class<T> classe, Long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            T t = session.get(classe, id);
            return t;
        } catch (RuntimeException rex) {
            rex.printStackTrace();
        }
        return null;
    }
}
