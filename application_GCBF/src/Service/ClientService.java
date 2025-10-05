package Service;

import DAO.ClientDAO;
import Entity.Client;
import java.util.List;
import java.util.Optional;

public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }

    public boolean ajouterClient(Client client) {
        return clientDAO.ajouterClient(client);
    }

    public boolean modifierClient(Client client) {
        return clientDAO.modifierClient(client);
    }

    public boolean supprimerClient(String id) {
        return clientDAO.supprimerClient(id);
    }

    public List<Client> listerClients() {
        return clientDAO.listerClients();
    }

    public Optional<Client> rechercherClientParId(String id) {
        return clientDAO.rechercherClientParId(id);
    }
}
