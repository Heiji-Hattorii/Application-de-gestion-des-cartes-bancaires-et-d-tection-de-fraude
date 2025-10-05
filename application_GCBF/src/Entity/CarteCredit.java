package Entity;

import java.time.LocalDate;

public final class CarteCredit extends Carte {

    private double plafondMensuel;
    private double tauxInteret;

    public CarteCredit(String id, String numero, LocalDate dateExpiration, StatutCarte statut,
                       String idClient, double plafondMensuel, double tauxInteret) {
        super(id, numero, dateExpiration, statut, idClient);
        this.plafondMensuel = plafondMensuel;
        this.tauxInteret = tauxInteret;
    }

    public double plafondMensuel() { return plafondMensuel; }
    public double tauxInteret() { return tauxInteret; }

    @Override
    public String toString() {
        return super.toString() + ", plafondMensuel=" + plafondMensuel +
                ", tauxInteret=" + tauxInteret + " }";
    }
}
