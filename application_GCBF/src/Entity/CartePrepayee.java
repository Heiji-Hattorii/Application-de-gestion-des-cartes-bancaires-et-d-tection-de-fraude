package Entity;

import java.time.LocalDate;

public final class CartePrepayee extends Carte {

    private double soldeDisponible;

    public CartePrepayee(String id, String numero, LocalDate dateExpiration, StatutCarte statut,
                         String idClient, double soldeDisponible) {
        super(id, numero, dateExpiration, statut, idClient);
        this.soldeDisponible = soldeDisponible;
    }

    public double soldeDisponible() { return soldeDisponible; }

    public void setSoldeDisponible(double soldeDisponible) {
        this.soldeDisponible = soldeDisponible;
    }

    @Override
    public String toString() {
        return super.toString() + ", soldeDisponible=" + soldeDisponible + " }";
    }
}
