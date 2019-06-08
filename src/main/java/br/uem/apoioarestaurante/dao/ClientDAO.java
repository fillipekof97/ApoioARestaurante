package br.uem.apoioarestaurante.dao;

import br.uem.apoioarestaurante.entities.Client;
import br.uem.apoioarestaurante.util.HibernateUtil;
import com.google.inject.Singleton;
import org.hibernate.Session;

/**
 * @author Maicon
 */
@Singleton
public class ClientDAO extends GenericDAO<Client> {

    protected ClientDAO() {
        super(Client.class);
    }

    public Client findByCpf(String cpf) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String query = "from " + Client.class.getSimpleName() + " c where c.cpf = " + cpf;
            return session.createQuery(query, Client.class).getSingleResult();
        } catch (RuntimeException rex) {
            rex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
