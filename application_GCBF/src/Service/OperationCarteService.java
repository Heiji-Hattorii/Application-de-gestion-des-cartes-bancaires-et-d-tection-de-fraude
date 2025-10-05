package Service;

import DAO.OperationCarteDAO;
import Entity.OperationCarte;
import Entity.TypeOperation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class OperationCarteService {
    private final OperationCarteDAO operationDAO;

    public OperationCarteService() {
        this.operationDAO = new OperationCarteDAO();
    }

    public boolean ajouterOperation(String id, double montant, TypeOperation type, String lieu, String idCarte) {
        OperationCarte op = new OperationCarte(id, LocalDateTime.now(), montant, type, lieu, idCarte);
        return operationDAO.ajouterOperation(op);
    }

    public Optional<OperationCarte> rechercherParId(String id) {
        return operationDAO.rechercherParId(id);
    }

    public List<OperationCarte> listerOperations() {
        return operationDAO.listerOperations();
    }

    public boolean supprimerOperation(String id) {
        return operationDAO.supprimerOperation(id);
    }
}
