package Service;

import DAO.CarteDAO;
import Entity.Carte;
import Entity.StatutCarte;

import java.util.List;

public class CarteService {
    private final CarteDAO carteDAO;

    public CarteService() {
        this.carteDAO = new CarteDAO();
    }

    public boolean ajouterCarte(Carte carte) {
        return carteDAO.ajouterCarte(carte);
    }

    public boolean modifierStatutCarte(String idCarte, StatutCarte statut) {
        return carteDAO.modifierStatutCarte(idCarte, statut);
    }

    public boolean supprimerCarte(String idCarte) {
        return carteDAO.supprimerCarte(idCarte);
    }

    public List<Carte> listerCartes() {
        return carteDAO.listerCartes();
    }

    public List<Carte> rechercherCartesParClient(String idClient) {
        return carteDAO.rechercherParClient(idClient);
    }
}
