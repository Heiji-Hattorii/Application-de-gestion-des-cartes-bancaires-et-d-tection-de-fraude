package Service;

import DAO.AlerteDAO;
import Entity.AlerteFraude;
import Entity.NiveauAlerte;
import java.util.List;
import java.util.Optional;

public class AlerteFraudeService {
    private AlerteDAO alerteDAO;

    public AlerteFraudeService() {
        this.alerteDAO = new AlerteDAO();
    }

    public boolean creerAlerte(String id, String description, NiveauAlerte niveau, String idCarte) {
        AlerteFraude alerte = new AlerteFraude(id, description, niveau, idCarte);
        return alerteDAO.ajouterAlerte(alerte);
    }

    public Optional<AlerteFraude> rechercherAlerte(String id) {
        return alerteDAO.rechercherParId(id);
    }

    public List<AlerteFraude> listerAlertes() {
        return alerteDAO.listerAlertes();
    }

    public boolean supprimerAlerte(String id) {
        return alerteDAO.supprimerAlerte(id);
    }
}
