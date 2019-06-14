package br.uem.apoioarestaurante.client;

import br.uem.apoioarestaurante.dao.ClientDAO;
import br.uem.apoioarestaurante.metadata.entities.Client;
import griffon.core.artifact.GriffonModel;
import griffon.metadata.ArtifactProviderFor;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Maicon
 */
@ArtifactProviderFor(GriffonModel.class)
public class ClientModel extends AbstractGriffonModel {

    @Inject
    private ClientDAO dao;

    @Inject
    private Client client;

    private LongProperty id;
    private StringProperty name;
    private StringProperty cpf;
    private ObjectProperty<LocalDate> birthDate;
    private StringProperty address;
    private StringProperty phoneNumber;

    private ObjectProperty<Client> clientProperty = new SimpleObjectProperty<>();

    private ObjectProperty<ObservableList<Client>> clients;

    public ClientModel() {
        id = new SimpleLongProperty();
        name = new SimpleStringProperty();
        cpf = new SimpleStringProperty();
        birthDate = new SimpleObjectProperty<>();
        address = new SimpleStringProperty();
        phoneNumber = new SimpleStringProperty();
        clients = new SimpleObjectProperty<>();
    }

    public void register() {
        getClientValues();

        Client c = dao.insert(client);

        consult();
    }

    public void consult() {
        clients.get().clear();
        clients.set(FXCollections.observableArrayList(dao.listAll()));
    }

    public void update() {
        getClientValues();

        dao.update(client);

        consult();
    }

    public void delete() {
        dao.delete(client);

        consult();
    }

    private void getClientValues() {
        if (client == null) {
            client = new Client();
        }
        client.setName(getName());
        client.setCpf(getCpf());
        client.setBirthDate(Date.from(getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        client.setAddress(getAddress());
        client.setPhoneNumber(getPhoneNumber());
    }

    public void setClientValues() {
        setName(client.getName());
        setCpf(client.getCpf());
        if (client.getBirthDate() instanceof java.sql.Date) {
            setBirthDate(((java.sql.Date) client.getBirthDate()).toLocalDate());
        } else {
            setBirthDate(client.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        setAddress(client.getAddress());
        setPhoneNumber(client.getPhoneNumber());
    }

    public ClientDAO getDao() {
        return dao;
    }

    public void setDao(ClientDAO dao) {
        this.dao = dao;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCpf() {
        return cpf.get();
    }

    public StringProperty cpfProperty() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public Client getClientProperty() {
        return clientProperty.get();
    }

    public ObjectProperty<Client> clientPropertyProperty() {
        return clientProperty;
    }

    public void setClientProperty(Client clientProperty) {
        this.clientProperty.set(clientProperty);
    }

    public ObservableList<Client> getClients() {
        return clients.get();
    }

    public ObjectProperty<ObservableList<Client>> clientsProperty() {
        return clients;
    }

    public void setClients(ObservableList<Client> clients) {
        this.clients.set(clients);
    }
}
