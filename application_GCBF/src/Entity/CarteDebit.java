package Entity;

import java.time.LocalDate;

public final class CarteDebit extends Carte {

    private double plafondJournalier;

    public CarteDebit(String id, String numero, LocalDate dateExpiration, StatutCarte statut,
                      String idClient, double plafondJournalier) {
        super(id, numero, dateExpiration, statut, idClient);
        this.plafondJournalier = plafondJournalier;
    }

    public double plafondJournalier() { return plafondJournalier; }

    @Override
    public String toString() {
        return super.toString() + ", plafondJournalier=" + plafondJournalier + " }";
    }
}
