package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class GenericDAO<T> {

    private Class<T> entityClass;

    protected GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T insert(T t) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return t;
        } finally {
            session.close();
        }
    }

    public boolean update(T t) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return true;
        } finally {
            session.close();
        }
    }

    public boolean delete(T t) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            return true;
        } finally {
            session.close();
        }
    }

    public T findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            T t = session.get(entityClass, id);
            return t;
        } finally {
            session.close();
        }
    }

    public List<T> listAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            Transaction transaction = session.beginTransaction();
            String query = "from " + entityClass.getSimpleName();
            return session.createQuery(query, entityClass).getResultList();
        } finally {
            session.close();
        }
    }
}
