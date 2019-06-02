package br.uem.apoioarestaurante.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Maicon
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public HibernateUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration annotation = new Configuration();
                sessionFactory = annotation.configure().buildSessionFactory();
            } catch (Throwable ex) {
                System.out.println("Erro ao inicar o Hibernte " + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }
}
