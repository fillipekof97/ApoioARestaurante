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
    public Client findById(Long id) {
        return super.findById(Client.class, id);
    }

    public Client findByCpf(String cpf) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String query = "from " + Client.class.getSimpleName() + " c where c.cpf = " + cpf;
            Client client = (Client) session.createQuery(query).getSingleResult();
            return client;
        } catch (RuntimeException rex) {
            rex.printStackTrace();
        }
        return null;
    }
}
